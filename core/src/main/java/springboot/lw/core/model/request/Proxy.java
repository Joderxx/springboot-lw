package springboot.lw.core.model.request;

import lombok.Data;

/**
 * @author xiejiedun on 2019/1/28
 */
@Data
public class Proxy {

    /**
     * 代理主机
     */
    private String host;
    /**
     * 代理端口
     */
    private String port;
    /**
     * 采用自动代理
     */
    private String autoProxy;
}
