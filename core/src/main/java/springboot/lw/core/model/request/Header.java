package springboot.lw.core.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiejiedun on 2019/1/28
 */
@Data
public class Header implements Serializable {

    private String key;
    private String value;
}
