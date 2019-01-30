package springboot.lw.coreweb.intercept;


import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import springboot.lw.core.service.UserService;
import springboot.lw.core.model.User;
import springboot.lw.core.util.Md5Util;
import springboot.lw.coreweb.config.ParamConfig;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Log4j2
public class LoginInterception implements HandlerInterceptor {

    @Autowired
    private ParamConfig paramConfig;
    @Reference
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String account = request.getParameter("account");
            String tid = request.getParameter("tid");
            String time = request.getParameter("time");
            String sign = request.getParameter("sign");
            if (StringUtils.isEmpty(sign)||StringUtils.isEmpty(account)||StringUtils.isEmpty(time)){
                response.sendRedirect(paramConfig.getProperty("login.host")+"/login");
            }
            if (StringUtils.isEmpty(tid)){
                tid = "0";
            }
            long t = Long.valueOf(time);
            //半小时
            if (System.currentTimeMillis()-t>1800000){
                response.sendRedirect(paramConfig.getProperty("login.host")+"/login");
            }
            String source = String.format("account=%s&tid=%s&time=%s",account,tid,time);
            String md5 = Md5Util.md5(source);
            if (!md5.equals(sign)){
                response.sendRedirect(paramConfig.getProperty("login.host")+"/login");
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String account = request.getParameter("account");
        String tid = null;

        if (modelAndView!=null&&modelAndView.getModel().containsKey("tid")){
            tid = String.valueOf(modelAndView.getModel().get("tid"));
        }
        if (StringUtils.isEmpty(tid)){
            tid = request.getParameter("tid");
            if (StringUtils.isEmpty(tid)){
                tid = "0";
            }
        }
        long time =System.currentTimeMillis();
        if (modelAndView!=null&&modelAndView.getModel().containsKey("time")){
            time = (long) modelAndView.getModel().get("time");
        }
        request.setAttribute("loginHost",paramConfig.getProperty("login.host"));
        request.setAttribute("mainHost",paramConfig.getProperty("main.host"));
        User user = userService.getUserByAccount(account);
        if (user!=null){
            String source = String.format("account=%s&tid=%s&time=%s",account,tid,time);
            String sign = Md5Util.md5(source);
            request.setAttribute("account",account);
            request.setAttribute("tid",tid);
            request.setAttribute("time",time);
            request.setAttribute("sign",sign);
            request.setAttribute("username",user.getUsername());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
