package springboot.lw.core.mapper.service;


import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Connection;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springboot.lw.core.model.*;
import springboot.lw.core.model.request.RequestData;
import springboot.lw.core.service.Crawl;
import springboot.lw.core.service.Excute;
import springboot.lw.core.service.TemplateService;

import java.util.concurrent.*;

@Service(interfaceClass = Excute.class)
@Component
@Log4j2
public class ExcuteImp implements Excute {

    private static final ExecutorService threadPool = new ThreadPoolExecutor(10, 20,
            0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());

    @Autowired
    private Crawl crawl;
    @Autowired
    private TemplateService templateService;

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

    @Override
    public boolean excute(RequestData requestData,long tid,long hid) throws Exception {
        threadPool.submit(() -> {
            TemplateHistory history = null;
            try {
                history = templateService.useTemplateHistory(tid,hid);
                history.setSuccess(TemplateHistory.SUCCESS);
                templateService.updateHistory(history);
                //TODO
            }catch (Exception e){
                if (history!=null){
                    history.setSuccess(TemplateHistory.FAIL);
                }
                log.error(e);
            }finally {
                if (history!=null){
                    templateService.updateHistory(history);
                }
            }
        });
        return true;
    }
}
