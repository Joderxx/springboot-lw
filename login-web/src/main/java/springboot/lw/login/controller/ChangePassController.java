package springboot.lw.login.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import springboot.lw.core.service.UserService;
import springboot.lw.login.dto.ChangePassDTO;
import springboot.lw.core.model.User;


@Controller
public class ChangePassController {

    @Reference
    private UserService userService;

    @GetMapping("/changePassword")
    public String changePage(){
        return "/changePass";
    }

    @PostMapping("/changePass")
    public String doChange(ChangePassDTO changePassDTO, Model model){
        model.addAttribute("username",changePassDTO.getUsername());
        boolean flag =true;
        if (StringUtils.isEmpty(changePassDTO.getUsername())){
            flag = false;
            model.addAttribute("usernameMsg","用户名/账号不能为空");
        }if (StringUtils.isEmpty(changePassDTO.getPassword())){
            flag = false;
            model.addAttribute("passwordMsg","旧密码不能为空");
        }if (StringUtils.isEmpty(changePassDTO.getNewPassword())){
            flag = false;
            model.addAttribute("newPasswordMsg","新密码不能为空");
        }
        if (!flag){
            return "/changePass";
        }
        User user = userService.getByAccountOrUsername(changePassDTO.getUsername(),
                changePassDTO.getPassword());
        if (user==null){
            model.addAttribute("passwordMsg","密码错误");
            return "/changePass";
        }else {
            boolean b = userService.updatePassword(user.getId(),changePassDTO.getPassword(),changePassDTO.getNewPassword());
            if (b){
                model.addAttribute("username",changePassDTO.getUsername());
                return "/login";
            }else {
                model.addAttribute("Msg","未知错误");
                return "/changePass";
            }

        }
    }
}
