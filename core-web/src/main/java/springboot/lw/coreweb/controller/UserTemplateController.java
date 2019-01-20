package springboot.lw.coreweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot.lw.core.model.User;
import springboot.lw.core.service.TemplateService;
import springboot.lw.core.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xiejiedun on 2019/1/16
 */
@Controller
@RequestMapping("/user/templates")
public class UserTemplateController extends BaseController {

    @Reference
    private UserService userService;

    @Reference
    private TemplateService templateService;

    /**
     * 得到用户模板
     * @return
     */
    @GetMapping("/private")
    public String getTemplate(HttpServletRequest request){
        String account = request.getParameter("account");
        User user = userService.getUserByAccount(account);
        if (user==null){
            return "404";
        }else {

        }
        return "";
    }

    /**
     * 公开模板
     * @return
     */
    @PostMapping("/publish")
    public String publicTemplate(){
        return "";
    }

    /**
     * 不公开公开模板
     * @return
     */
    @PostMapping("/unPublish")
    public String unPublicTemplate(){
        return "";
    }

    /**
     * 查看模板结果
     * @return
     */
    @GetMapping("/result")
    public String getResult(){
        return "";
    }

    /**
     * 得到历史数据
     * @return
     */
    @GetMapping("/history")
    public String getHistory(){
        return "";
    }

}
