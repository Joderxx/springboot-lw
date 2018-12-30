package springboot.lw.core.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ExcuteParameter {

    /**
     * 爬取url
     */
    private String url;
    /**
     * 爬取方法，如get,post,等
     */
    private String method;
    /**
     * 传递参数
     */
    private Map<String,String> data;
    /**
     * json传递参数
     */
    private String requestBody;
    /**
     * 设置cookies
     */
    private Map<String,String> cookies;
    /**
     * 设置headers
     */
    private Map<String,String> headers;
    /**
     * 代理ip
     */
    private String proxyHost;
    /**
     * 代理端口
     */
    private int proxyPort;
    /**
     * 需要登录时，传递的登录url（form中的action对应的url）
     */
    private String loginUrl;
    /**
     * 登录数据，如账号密码
     */
    private Map<String,String> loginData;
    /**
     * 是否添加过滤条件
     */
    private boolean filter;

    private List<String> selectConditions;

    private List<String> filterConditions;

}
