package springboot.lw.core.model;

import lombok.Data;

/**
 * 模板运行结果
 */
@Data
public class TemplateResult {

    /**
     * 对应hid
     */
    private long hid;
    /**
     * 结果
     */
    private String result;
}
