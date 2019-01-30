package springboot.lw.core.model.request;

import lombok.Data;

import java.util.List;

/**
 * @author xiejiedun on 2019/1/28
 */
@Data
public class RequestData {

    /**
     * 模板名字
     */
    private String templateName;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 请求url
     */
    private String url;
    /**
     * 父条件
     */
    private List<Condition> commons;
    /**
     * 获取的字段
     */
    private List<Field> fields;
    /**
     * 头部
     */
    private List<Header> headers;
    /**
     * 代理
     */
    private Proxy proxy;

}
