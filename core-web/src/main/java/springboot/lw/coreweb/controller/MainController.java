package springboot.lw.coreweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springboot.lw.core.model.Template;
import springboot.lw.core.model.User;
import springboot.lw.core.service.TemplateService;
import springboot.lw.core.service.UserService;
import springboot.lw.coreweb.config.ParamConfig;

@CrossOrigin
@Controller
@RequestMapping("/user")
@Log4j2
public class MainController extends BaseController{

    @Autowired
    private ParamConfig paramConfig;
    @Reference
    private UserService userService;
    @Reference
    private TemplateService templateService;

    @GetMapping("/main")
    public String main(@RequestParam(value = "account",defaultValue = "") String account,
                       @RequestParam(value = "tid",defaultValue = "") String tid,
                       Model model){
        User user = userService.getUserByAccount(account);
        if (user==null){
            return "404";
        }
        try {
            if (StringUtils.isEmpty(tid)){
                Template template = templateService.getTemplateLastEdit(user.getId());
                if (template==null){
                    model.addAttribute("templateType","user-main");
                }else {
                    model.addAttribute("template",template);
                    model.addAttribute("templateType","user-operator");
                    model.addAttribute("tid",template.getTid());
                }
            }else {
                Template template = templateService.getTemplateDetailById(Long.parseLong(tid));
                model.addAttribute("template",template);
                model.addAttribute("templateType","user-operator");
                model.addAttribute("tid",template.getTid());
            }
            return "panel";
        }catch (Exception e){
            log.error(e);
            return "500";
        }

    }
}
