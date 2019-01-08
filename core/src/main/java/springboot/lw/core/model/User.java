package springboot.lw.core.model;

import lombok.Data;

@Data
public class User {

    private long id;
    private String username;
    private String account;
    private String password;
    private long createTime;
}
