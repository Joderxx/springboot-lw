package springboot.lw.login.dto;

import lombok.Data;

@Data
public class ChangePassDTO {

    private String username;
    private String password;
    private String newPassword;
}
