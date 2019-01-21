package springboot.lw.coreweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String getTemplate(HttpServletRequest request,Model model){
        String account = request.getParameter("account");
        User user = userService.getUserByAccount(account);
        if (user==null){
            return "404";
        }else {
            model.addAttribute("templateType","user-template");
        }
        return "panel";
    }

    /**
     * 公开模板
     * @return
     */
    @ResponseBody
    @PostMapping("/publish")
    public String publicTemplate(@RequestParam("tid") String tid,
                                 @RequestParam("type")String type){
        try {
            boolean flag = false;
            if ("publish".equalsIgnoreCase(type)){
                flag = true;
            }
            boolean b = templateService.updateTemplatePublish(Long.parseLong(tid), false);
            if (b){
                return "{\"state\": 1,\"msg\": \"成功\"}";
            }else {
                return "{\"state\": 0,\"msg\": \"失败\"}";
            }
        }catch (Exception e){
            return "{\"state\": 0,\"msg\": \"失败\"}";
        }

    }


    /**
     * 得到历史数据
     * @return
     */
    @GetMapping("/history")
    public String getHistory(@RequestParam("tid") String tid, Model model){
        model.addAttribute("templateType","history-template");
        return "panel";
    }

}
