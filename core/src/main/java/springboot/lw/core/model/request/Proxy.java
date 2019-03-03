package springboot.lw.core.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiejiedun on 2019/1/28
 */
@Data
public class Proxy implements Serializable {

    /**
     * 代理主机
     */
    private String host;
    /**
     * 代理端口
     */
    private Integer port;
    /**
     * 采用自动代理
     */
    private String autoProxy;
}
