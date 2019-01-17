package springboot.lw.core.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 模板运行结果
 */
@Data
public class TemplateResult implements Serializable {

    /**
     * 对应hid
     */
    private long hid;
    /**
     * 结果
     */
    private String result;
}
