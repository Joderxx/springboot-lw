package springboot.lw.coreweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot.lw.core.model.Template;
import springboot.lw.core.model.TemplateHistory;
import springboot.lw.core.model.User;
import springboot.lw.core.service.TemplateService;
import springboot.lw.core.service.UserService;
import springboot.lw.core.util.Md5Util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xiejiedun on 2019/1/16
 */
@Controller
@RequestMapping("/templates")
@Log4j2
public class TemplateController extends BaseController {

    @Reference
    private TemplateService templateService;
    @Reference
    private UserService userService;

    /**
     * 运用某个模板
     * @return
     */
    @GetMapping("/use")
    public String useTemplate(HttpServletRequest request){
        String tid = request.getParameter("tid");
        String account = request.getParameter("account");
        User user = userService.getUserByAccount(account);
        if (user==null){
            return "404";
        }
        try {
            Template template = templateService.getTemplateDetailById(Long.parseLong(tid));
            long time = System.currentTimeMillis();
            if (user.getId()!=template.getUser().getId()){
                template.setCreateTime(time);
                template.setModifiedTime(time);
                template.setPublish(false);
                template.setUser(user);
                templateService.saveTemplate(template);
                String md5 = Md5Util.md5(String.format("account=%s&tid=%s&time=%s",user.getAccount(),template.getTid(),time));
                return "redirect:/user/main?account="+user.getAccount()+"&tid="+template.getTid()+"&time="+time+"&sign="+md5;
            }
            String md5 = Md5Util.md5(String.format("account=%s&tid=%s&time=%s",user.getAccount(),tid,time));
            return "redirect:/user/main?account="+user.getAccount()+"&tid="+tid+"&time="+time+"&sign="+md5;
        }catch (Exception e){
            log.error(e);
            return "500";
        }

    }


    /**
     * 查看模板结果
     * @return
     */
    @GetMapping("/result")
    public String getResult(){
        return "";
    }
}
