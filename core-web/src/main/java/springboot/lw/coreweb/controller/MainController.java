package springboot.lw.coreweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springboot.lw.core.service.UserService;
import springboot.lw.coreweb.config.ParamConfig;

@CrossOrigin
@Controller
@RequestMapping("/user")
public class MainController extends BaseController{

    @Autowired
    private ParamConfig paramConfig;
    @Reference
    private UserService userService;

    @GetMapping("/main")
    public String main(@RequestParam(value = "account",defaultValue = "") String account,
                       @RequestParam(value = "time",defaultValue = "0") long time,
                       @RequestParam(value = "sign",defaultValue = "") String sign,
                       Model model){
        model.addAttribute("templateType","user-main");
        return "panel";
    }
}
