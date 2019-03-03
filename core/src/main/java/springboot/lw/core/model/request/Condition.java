package springboot.lw.core.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiejiedun on 2019/1/28
 */
@Data
public class Condition implements Serializable {

    /**
     * 条件类型
     */
    private String condition;
    /**
     * 筛选条件
     */
    private String conditionText;

    /**
     * index
     */
    private Integer index = 0;
    /**
     * 选择文本还是属性
     */
    private String select;
    /**
     * 选择属性时，对应属性名
     */
    private String attrText;
}
