package springboot.lw.core.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 模板提交记录
 */
@Data
public class TemplateHistory implements Serializable {

    /**
     * id
     */
    private long hid;
    /**
     * 修改时间
     */
    private long modifiedTime;
    /**
     * 请求内容
     */
    private String content;
    /**
     * 是否成功
     */
    private boolean sucess;
    /**
     * 对应Template
     */
    private Template template;
}
