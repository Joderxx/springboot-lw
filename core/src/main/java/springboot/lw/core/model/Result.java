package springboot.lw.core.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class Result implements Serializable {

    /**
     * 整个的内容
     */
    private String body;
    /**
     * 头部信息
     */
    private Map<String,String> headers;
    /**
     * 状态码
     */
    private int statusCode;
    /**
     * 状态信息
     */
    private String statusMessage;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 类型
     */
    private String contentType;
    /**
     * 编码
     */
    private String charset;

    private List<Map<String,String>> data;
}
