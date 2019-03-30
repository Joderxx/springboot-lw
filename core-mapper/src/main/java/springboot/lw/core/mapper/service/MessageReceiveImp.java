package springboot.lw.core.mapper.service;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Connection;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import springboot.lw.core.model.TemplateHistory;
import springboot.lw.core.model.TemplateResult;
import springboot.lw.core.model.dto.ExcuteDTO;
import springboot.lw.core.model.request.Condition;
import springboot.lw.core.model.request.Field;
import springboot.lw.core.model.request.RequestData;
import springboot.lw.core.service.Crawl;
import springboot.lw.core.service.TemplateService;
import springboot.lw.core.service.adapter.MessageReceiveAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class MessageReceiveImp extends MessageReceiveAdapter {

    @Autowired
    private Crawl crawl;
    @Autowired
    private TemplateService templateService;

    @RabbitHandler
    @RabbitListener(
            bindings =
            @QueueBinding(
                    value = @Queue(value = "task",durable = "true"),
                    exchange = @Exchange(value = "task-exchange",type="topic"),
                    key = "task*"
            )
    )
    @Override
    public void receive(@Payload ExcuteDTO excuteDTO,
                        @Headers Map<String, Object> headers,
                        Channel channel) throws IOException {
        execute(excuteDTO.getRequestData(),excuteDTO.getTid(),excuteDTO.getHid());
        //获取消息的标记
        Long deliveryTag =
                (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        // 因为设置了手动接收，所以手动消费消息，
        //如果没有这句，消息将一直在rabbitMQ中
        channel.basicAck(deliveryTag,false);
    }

    private void execute(RequestData requestData,long tid,long hid){
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
