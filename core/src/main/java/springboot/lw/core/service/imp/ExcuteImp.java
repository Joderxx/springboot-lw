package springboot.lw.core.service.imp;


import org.jsoup.Connection;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.lw.core.model.ExcuteParameter;
import springboot.lw.core.model.FilterParam;
import springboot.lw.core.model.RequestParam;
import springboot.lw.core.model.Result;
import springboot.lw.core.service.Crawl;
import springboot.lw.core.service.Excute;

@Service
public class ExcuteImp implements Excute {

    @Autowired
    private Crawl crawl;

    @Override
    public Result excute(ExcuteParameter parameter) throws Exception {
        Result result = new Result();
        RequestParam requestParam = parameter.getRequestParam();
        FilterParam filterParam = parameter.getFilterParam();
        Connection connection = crawl.connect(requestParam.getUrl()).ignoreContentType(true);
        connection = crawl.data(connection,requestParam.getData());
        connection = crawl.method(connection,requestParam.getMethod());
        connection = crawl.doLogin(connection,
                requestParam.getLoginUrl(),requestParam.getLoginData());
        connection = crawl.cookies(connection,requestParam.getCookies());
        connection = crawl.headers(connection,requestParam.getHeaders());
        connection = crawl.proxy(connection,requestParam.getProxyHost(),requestParam.getProxyPort());
        connection = crawl.requestBody(connection,requestParam.getRequestBody());
        Connection.Response response = crawl.excute(connection);

        result.setMethod(requestParam.getMethod().toUpperCase());
        result.setCharset(crawl.charset(response));
        result.setHeaders(crawl.headers(response));
        result.setStatusCode(crawl.statusCode(response));
        result.setStatusMessage(crawl.statusMessage(response));
        result.setCharset(crawl.charset(response));

        if (filterParam==null){
            result.setBody(crawl.body(response));
            result.setContentType(crawl.contentType(response));
        }else {
            Element body = crawl.parseHtml(crawl.body(response));

        }

        return result;
    }
}
