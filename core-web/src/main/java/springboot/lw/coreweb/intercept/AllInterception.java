package springboot.lw.coreweb.intercept;


import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import springboot.lw.core.model.User;
import springboot.lw.core.service.UserService;
import springboot.lw.core.util.Md5Util;
import springboot.lw.coreweb.config.ParamConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AllInterception implements HandlerInterceptor {

    @Autowired
    private ParamConfig paramConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String account = request.getParameter("account");
        String tid = request.getParameter("tid");
        String time = request.getParameter("time");
        String sign = request.getParameter("sign");
        if (StringUtils.isEmpty(sign)){
            return true;
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
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
