package springboot.lw.core.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 模板提交记录
 */
@Data
public class TemplateHistory implements Serializable {
    public static final int SUCCESS=1,FAIL=2,RUN=3,DEFAULT=0;
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
     * 0 默认 1成功 2失败 3执行中
     */
    private int success;
    /**
     * 对应Template
     */
    private Template template;
}
