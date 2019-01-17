package springboot.lw.core.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserImage implements Serializable {

    /**
     * id
     */
    private long id;
    /**
     * 用户账号
     */
    private String account;
    /**
     * 图片
     */
    private byte[] image;
    /**
     * 创建时间
     */
    private long createTime;

    /**
     * 修改时间
     */
    private long modifiedTime;
    /**
     * 修改方式
     */
    private String mode = "insert";
}
