package springboot.lw.coreweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import springboot.lw.core.model.Template;
import springboot.lw.core.model.TemplateHistory;
import springboot.lw.core.model.request.RequestData;
import springboot.lw.core.service.Excute;
import springboot.lw.core.service.TemplateService;

/**
 * @author xiejiedun on 2019/1/5
 */
@Controller
@RequestMapping("/user/crawl")
public class DealController extends BaseController{

    @Reference
    private Excute excute;
    @Reference
    private TemplateService templateService;


    @PostMapping("/process")
    public String process(@RequestParam(name = "requestData",defaultValue = "") String data,
                          @RequestParam(name = "tid",defaultValue = "0") String tid){
        long time = System.currentTimeMillis();
        RequestData requestData = JSON.parseObject(data,RequestData.class);
        if (requestData==null){
            return "417";
        }
        try {
            Template template = templateService.getTemplateById(Long.parseLong(tid));
            if (template==null){
                return "404";
            }
            if (!StringUtils.isEmpty(requestData.getTemplateName())&&!requestData.getTemplateName().equals(template.getName())){
                template.setName(requestData.getTemplateName());
            }
            template.setModifiedTime(time);
            TemplateHistory history = templateService.getHistoryLastByTid(Long.parseLong(tid));
            if (history==null){
                history = new TemplateHistory();
                history.setTemplate(template);
                history.setModifiedTime(time);
                history.setContent(data);
                history.setSuccess(TemplateHistory.RUN);
                templateService.addHistory(history);
            }else if (!data.equals(history.getContent())){
                history.setContent(data);
                history.setModifiedTime(time);
                templateService.addHistory(history);
            }
            excute.excute(requestData,template.getTid(),history.getHid());
        } catch (Exception e) {
            return "500";
        }
        return "200";
    }
}
