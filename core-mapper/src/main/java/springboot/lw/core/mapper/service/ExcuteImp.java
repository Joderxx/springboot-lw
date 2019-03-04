package springboot.lw.core.mapper.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Connection;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import springboot.lw.core.model.*;
import springboot.lw.core.model.request.Condition;
import springboot.lw.core.model.request.Field;
import springboot.lw.core.model.request.RequestData;
import springboot.lw.core.service.Crawl;
import springboot.lw.core.service.Excute;
import springboot.lw.core.service.TemplateService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        /*threadPool.submit(() -> {
            TemplateHistory history = null;
            try {
                history = templateService.useTemplateHistory(tid,hid);
                history.setSuccess(TemplateHistory.SUCCESS);
                templateService.updateHistory(history);
                List list = dealRequest(requestData);
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
        });*/
        TemplateHistory history = null;
        try {
            history = templateService.useTemplateHistory(tid,hid);
            history.setSuccess(TemplateHistory.SUCCESS);
            templateService.updateHistory(history);
            List list = dealRequest(requestData);
            TemplateResult result = new TemplateResult();
            result.setHid(hid);
            result.setResult(JSON.toJSONString(list));
            try {
                templateService.addResult(result);
            }catch (Exception e){
                templateService.updateResult(result);
            }
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
        return true;
    }

    private List dealRequest(RequestData requestData) throws Exception {
        List list = new ArrayList();
        Connection connection = crawl.connect(requestData.getUrl()).ignoreContentType(true);
        connection = crawl.method(connection,requestData.getMethod());
        connection = crawl.headers(connection,requestData.headMap());
        Element body = connection.get().body();
        if (!CollectionUtils.isEmpty(requestData.getFields())){
            Elements elements = null;
            if (!CollectionUtils.isEmpty(requestData.getCommons())){
                elements = dealCommon(requestData,body);
                list = dealField(requestData,elements);
            }

        }else {
            Map<String,String> map = new HashMap<>();
            map.put("body",body.wholeText());
            list.add(map);
        }
        return list;
    }

    private Elements dealCommon(RequestData requestData,Element body) {
        Elements elements = new Elements(body);
        for (Condition condition: requestData.getCommons()){
            Elements es = new Elements();
            for (Element e: elements){
                Elements t = dealCondition(condition,e);
                if (t.size()>0){
                    es.addAll(t);
                }
            }
            elements = es;
        }
        return elements;
    }

    private List dealField(RequestData requestData,Elements elements){
        List list = new ArrayList();
        for (Element element: elements){
            Map<String,String> map = new HashMap<>();
            for (Field field: requestData.getFields()){
                Element es = element;
                int i=0;
                for (;i<field.getCondition().size()-1;i++){
                    es = dealCondition1(field.getCondition().get(i),es);
                }
                String str = dealLastCondition(field.getCondition().get(i),es);
                map.put(field.getFieldName(),str);
            }
            list.add(map);
        }
        return list;
    }

    private Elements dealCondition(Condition condition,Element element){
        Elements elements = new Elements();
        if (condition.getCondition().equalsIgnoreCase("ID")){
            Element e = element.getElementById(condition.getConditionText());
            elements.add(e);
        }else if (condition.getCondition().equalsIgnoreCase("CLASS")){
            elements.addAll(element.getElementsByClass(condition.getConditionText()));
        }else if (condition.getCondition().equalsIgnoreCase("TAG")){
            elements.addAll(element.getElementsByTag(condition.getConditionText()));
        }else if (condition.getCondition().equalsIgnoreCase("XPATH")){

        }else if (condition.getCondition().equalsIgnoreCase("REGEX")){

        }
        return elements;
    }

    private Element dealCondition1(Condition condition,Element element){
        if (condition.getCondition().equalsIgnoreCase("ID")){
            return element.getElementById(condition.getConditionText());
        }else if (condition.getCondition().equalsIgnoreCase("CLASS")){
            return element.getElementsByClass(condition.getConditionText()).get(condition.getIndex());
        }else if (condition.getCondition().equalsIgnoreCase("TAG")){
            return element.getElementsByTag(condition.getConditionText()).get(condition.getIndex());
        }else if (condition.getCondition().equalsIgnoreCase("XPATH")){

        }else if (condition.getCondition().equalsIgnoreCase("REGEX")){

        }
        return null;
    }

    private String dealLastCondition(Condition condition,Element element){
        if (condition.getCondition().equalsIgnoreCase("ID")){
            Element e = element.getElementById(condition.getConditionText());
            return dealElement(condition,e);
        }else if (condition.getCondition().equalsIgnoreCase("CLASS")){
            Element e = element.getElementsByClass(condition.getConditionText()).get(condition.getIndex());
            return dealElement(condition,e);
        }else if (condition.getCondition().equalsIgnoreCase("TAG")){
            Element e = element.getElementsByTag(condition.getConditionText())
                    .get(condition.getIndex());
            return dealElement(condition,e);
        }else if (condition.getCondition().equalsIgnoreCase("XPATH")){

        }else if (condition.getCondition().equalsIgnoreCase("REGEX")){

        }
        return null;
    }

    private String dealElement(Condition condition,Element element){
        if (condition.getSelect().equalsIgnoreCase("TEXT")){
            return element.text();
        }else {
            return element.attr(condition.getAttrText());
        }
    }
}
