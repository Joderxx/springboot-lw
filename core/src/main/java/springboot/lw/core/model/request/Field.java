package springboot.lw.core.model.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author xiejiedun on 2019/1/29
 */
@Data
public class Field implements Serializable {

    private String fieldName;
    private List<Condition> condition;
}
