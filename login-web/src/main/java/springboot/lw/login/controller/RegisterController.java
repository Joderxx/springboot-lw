package springboot.lw.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import springboot.lw.login.dto.RegisterDTO;
import springboot.lw.login.model.User;
import springboot.lw.login.service.UserService;
import springboot.lw.login.util.Md5Util;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register(){
        return "/register";
    }

    @PostMapping("/doRegister")
    public String doRegister(RegisterDTO registerDTO, Model model){
        model.addAttribute("username",registerDTO.getUsername());
        model.addAttribute("account",registerDTO.getAccount());
        boolean flag = true;
        if (StringUtils.isEmpty(registerDTO.getAccount())){
            model.addAttribute("accountMsg","账号不能为空");
            flag = false;
        }if (StringUtils.isEmpty(registerDTO.getUsername())){
            model.addAttribute("usernameMsg","用户名不能为空");
            flag = false;
        }if (StringUtils.isEmpty(registerDTO.getPassword())){
            model.addAttribute("passwordMsg","密码不能为空");
            flag = false;
        }
        if (!flag){
            return "/register";
        }
        User user = registerDTO.toUser();
        user.setPassword(Md5Util.md5(user.getPassword()));
        UserService.Return insert = userService.insert(user);
        if (insert== UserService.Return.REPEAT_ACCOUNT){
            model.addAttribute("accountMsg","账号已存在");
        }else if (insert== UserService.Return.FAIL){
            model.addAttribute("msg","未知错误");
        }else {
            model.addAttribute("username",user.getAccount());
            return "redirect:/login";
        }
        return "/register";
    }
}
