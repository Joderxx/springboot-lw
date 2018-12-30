package springboot.lw.core.service;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Map;

public interface Crawl {

    Connection connect(String url);

    Connection doLogin(Connection connection, String loginUrl, Map<String, String> data);

    Connection method(Connection connection, String method);

    Connection data(Connection connection, Map<String, String> data);

    Connection headers(Connection connection, Map<String, String> header);

    Connection cookies(Connection connection, Map<String, String> cookies);

    Connection requestBody(Connection connection, String body);

    Connection proxy(Connection connection, String host, int port);

    Response excute(Connection connection) throws Exception;

    String charset(Response response);

    int statusCode(Response response);

    String statusMessage(Response response);

    String contentType(Response response);

    Map<String,String> cookies(Response response);

    Map<String,String> headers(Response headers);

    String body(Response response);

    Element parseHtml(String body);

    Element getElementById(Element element, String id);

    Elements getElementByClassName(Element element, String className);

    Elements getElementsByAttribute(Element element, String key);

    Elements getFirstElementsByAttribute(Element element, String key);

    Elements getElementsByTag(Element element, String tag);

    Elements getFirstElementsByTag(Element element, String tag);

    Elements select(Element element, String selectQuery);

    Element selectFirst(Element element, String selectQuery);

    String data(Element element);

    String text(Element element);
}