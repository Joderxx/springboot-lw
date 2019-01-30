package springboot.lw.core.model.request;

import lombok.Data;

/**
 * @author xiejiedun on 2019/1/28
 */
@Data
public class Condition {

    /**
     * 条件类型
     */
    private String condition;
    /**
     * 筛选条件
     */
    private String conditionText;
    /**
     * 选择文本还是属性
     */
    private String select;
    /**
     * 选择属性时，对应属性名
     */
    private String attrText;
}
