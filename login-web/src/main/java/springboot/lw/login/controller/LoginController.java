package springboot.lw.login.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import springboot.lw.core.service.UserService;
import springboot.lw.login.config.ParamConfig;
import springboot.lw.login.dto.LoginDTO;
import springboot.lw.core.model.User;
import springboot.lw.core.util.Md5Util;

@Controller
public class LoginController {

    @Reference
    private UserService userService;
    @Autowired
    private ParamConfig paramConfig;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/login")
    public String login(Model model){
        return "/login";
    }

    @PostMapping("/doLogin")
    public String doLogin(Model model,LoginDTO loginDTO){
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        model.addAttribute("username",username);
        User user = userService.getByAccountOrUsername(username,password);
        if (user==null){
            model.addAttribute("passwordMsg","账号或密码错误");
            return "/login";
        }else {
            long time = System.currentTimeMillis();
            String account = user.getAccount();
            String sign = Md5Util.md5("account="+user.getAccount()+"&time="+time);
            return "redirect:"+paramConfig.getProperty("main.url")+
                    "/user/main?account="+account+"&time="+time+"&sign="+sign;
        }

    }
}
