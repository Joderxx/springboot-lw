package springboot.lw.login.dto;

import lombok.Data;
import springboot.lw.core.model.User;

@Data
public class RegisterDTO {

    private String account;
    private String username;
    private String password;

    public User toUser(){
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        user.setAccount(account);
        user.setCreateTime(System.currentTimeMillis()/1000);
        return user;
    }
}
