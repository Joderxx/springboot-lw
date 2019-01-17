package springboot.lw.core.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 对应用户模板
 */
@Data
public class Template implements Serializable {

    /**
     * 创建模板id
     */
    private long tid;
    /**
     * 名称
     */
    private String name;
    /**
     * 创建时间
     */
    private long createTime;
    /**
     * 是否公开
     */
    private boolean publish;

    /**
     * 描述
     */
    private String description;

    /**
     * 对应user id
     */
    private long uid;
}
