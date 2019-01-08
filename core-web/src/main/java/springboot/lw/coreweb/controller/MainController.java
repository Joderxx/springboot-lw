package springboot.lw.coreweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springboot.lw.coreweb.config.ParamConfig;
import springboot.lw.coreweb.model.User;
import springboot.lw.coreweb.service.UserService;
import springboot.lw.coreweb.util.Md5Util;

@CrossOrigin
@Controller
@RequestMapping("/user")
public class MainController {

    @Autowired
    private ParamConfig paramConfig;
    @Autowired
    private UserService userService;

    @GetMapping("/main")
    public String main(@RequestParam(value = "account",defaultValue = "") String account,
                       @RequestParam(value = "time",defaultValue = "0") long time,
                       @RequestParam(value = "sign",defaultValue = "") String sign,
                       Model model){

        return "panel";
    }
}
