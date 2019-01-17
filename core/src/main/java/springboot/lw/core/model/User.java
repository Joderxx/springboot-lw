package springboot.lw.core.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private long id;
    private String username;
    private String account;
    private String password;
    private long createTime;
}
