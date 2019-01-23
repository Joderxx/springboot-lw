package springboot.lw.core.mapper.service;


import com.alibaba.dubbo.config.annotation.Service;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import springboot.lw.core.exception.ParameterException;
import springboot.lw.core.service.Crawl;

import java.io.IOException;
import java.util.Map;

@Service(interfaceClass = Crawl.class)
@Component
@PropertySource("classpath:application.properties")
public class CrawlImp implements Crawl {

    @Value("${request.method}")
    private String requestMethod;
    private String[] requestMethods;
    @Override
    public Connection connect(String url) {
        if (StringUtils.isEmpty(url)){
            throw new ParameterException("url不能为空");
        }
        return Jsoup.connect(url);
    }

    @Override
    public Connection doLogin(Connection connection, String loginUrl, Map<String, String> data) {
        if (!StringUtils.isEmpty(loginUrl)&& data!=null&& data.size()>0){
            Connection.Request request = connect(loginUrl).data(data).request();
            return connection.request(request);
        }
        return connection;
    }

    @Override
    public Connection method(Connection connection, String method) {
        if (StringUtils.isEmpty(method)){
            throw new ParameterException("请求方法不能为空");
        }
        method = method.toUpperCase();
        if (requestMethods==null){
            synchronized (this){
                if (requestMethods==null&&requestMethod.length()>0){
                    String[] methods = requestMethod.split(",");
                    requestMethods = new String[methods.length];
                    for (int i = 0; i < methods.length; i++) {
                        requestMethods[i] = methods[i].toUpperCase();
                    }
                }
            }
        }
        boolean flag = false;
        for (String m:requestMethods){
            if (m.equals(method)){
                flag = true;
                break;
            }
        }
        if (!flag){
            throw new ParameterException("请求方法类型不存在");
        }
        return connection.method(Connection.Method.valueOf(method));
    }

    @Override
    public Connection data(Connection connection, Map<String, String> data) {
        if (data!=null&&data.size()>0){
            connection = connection.data(data);
        }
        return connection;
    }

    @Override
    public Connection headers(Connection connection, Map<String, String> header) {
        if (header!=null&&header.size()>0){
            connection = connection.headers(header);
        }
        return connection;
    }

    @Override
    public Connection cookies(Connection connection, Map<String, String> cookies) {
        if (cookies!=null&&cookies.size()>0){
            connection = connection.cookies(cookies);
        }
        return connection;
    }

    @Override
    public Connection requestBody(Connection connection, String body) {
        if (!StringUtils.isEmpty(body)){
            connection = connection.requestBody(body);
        }
        return connection;
    }

    @Override
    public Connection proxy(Connection connection, String host, int port) {
        if (!StringUtils.isEmpty(host)){
            if (port<=0||port>65535){
                throw new ParameterException("端口必须在1到65535之间");
            }
            connection = connection.proxy(host,port);
        }
        return connection;
    }

    @Override
    public Connection.Response excute(Connection connection) throws IOException {
        return connection.execute();
    }

    @Override
    public String charset(Connection.Response response) {
        return response.charset();
    }

    @Override
    public int statusCode(Connection.Response response) {
        return response.statusCode();
    }

    @Override
    public String statusMessage(Connection.Response response) {
        return response.statusMessage();
    }

    @Override
    public String contentType(Connection.Response response) {
        return response.contentType();
    }

    @Override
    public Map<String, String> cookies(Connection.Response response) {
        return response.cookies();
    }

    @Override
    public Map<String, String> headers(Connection.Response response) {
        return response.headers();
    }

    @Override
    public String body(Connection.Response response) {
        return response.body();
    }

    @Override
    public Element parseHtml(String body) {
        if (StringUtils.isEmpty(body)){
            return null;
        }
        return Jsoup.parse(body).body();
    }

    @Override
    public Element getElementById(Element element, String id) {
        return null;
    }

    @Override
    public Elements getElementByClassName(Element element, String className) {
        return null;
    }

    @Override
    public Elements getElementsByAttribute(Element element, String key) {
        return null;
    }

    @Override
    public Elements getFirstElementsByAttribute(Element element, String key) {
        return null;
    }

    @Override
    public Elements getElementsByTag(Element element, String tag) {
        return null;
    }

    @Override
    public Elements getFirstElementsByTag(Element element, String tag) {
        return null;
    }

    @Override
    public Elements select(Element element, String selectQuery) {
        return null;
    }

    @Override
    public Element selectFirst(Element element, String selectQuery) {
        return null;
    }

    @Override
    public String data(Element element) {
        return null;
    }

    @Override
    public String text(Element element) {
        return null;
    }


}
