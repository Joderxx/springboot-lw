package springboot.lw.coreweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import springboot.lw.core.model.Template;
import springboot.lw.core.model.TemplateHistory;
import springboot.lw.core.model.TemplateResult;
import springboot.lw.core.model.User;
import springboot.lw.core.model.request.RequestData;
import springboot.lw.core.service.TemplateService;
import springboot.lw.core.service.UserService;
import springboot.lw.core.util.Md5Util;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author xiejiedun on 2019/1/16
 */
@Controller
@RequestMapping("/user/templates")
@Log4j2
public class UserTemplateController extends BaseController {

    @Reference
    private UserService userService;

    @Reference
    private TemplateService templateService;

    @PostMapping("/create")
    public String createTemplate(HttpServletRequest request,Model model){
        String account = request.getParameter("account");
        User user = userService.getUserByAccount(account);
        String name = request.getParameter("templateName");
        String description = request.getParameter("description");
        if (StringUtils.isEmpty(name)){
            name = Md5Util.md5(UUID.randomUUID().toString()).substring(0,6);
        }
        Template template = new Template();
        template.setName(name);
        template.setPublish(false);
        template.setCreateTime(System.currentTimeMillis());
        template.setModifiedTime(System.currentTimeMillis());
        template.setDescription(description);
        template.setUser(user);
        templateService.saveTemplate(template);
        model.addAttribute("template",template);
        model.addAttribute("requestData",new RequestData());
        model.addAttribute("templateType","user-operator");
        return "panel";
    }

    /**
     * 得到用户模板
     * @return
     */
    @GetMapping("/private")
    public String getTemplate(HttpServletRequest request,Model model){
        String account = request.getParameter("account");
        User user = userService.getUserByAccount(account);
        if (user==null){
            return "404";
        }else {
            List<Map<String, Object>> userTemplates = templateService.getUserTemplates(user.getId());
            if (userTemplates==null||userTemplates.size()==0){
                userTemplates = new ArrayList<>();
            }
            long time = System.currentTimeMillis();
            for (Map<String,Object> map:userTemplates){
                String md5 = Md5Util.md5(String.format("account=%s&tid=%s&time=%s",account,map.get("tid"),time));
                map.put("sign",md5);

            }
            model.addAttribute("time",time);
            model.addAttribute("templateList",userTemplates);
            model.addAttribute("templateType","user-template");
        }
        return "panel";
    }

    /**
     * 公开模板
     * @return
     */
    @ResponseBody
    @PostMapping("/publish")
    public String publicTemplate(@RequestParam("tid") String tid,
                                 @RequestParam("type")String type){
        try {
            boolean flag = Boolean.parseBoolean(type);
            boolean b = templateService.updateTemplatePublish(Long.parseLong(tid), flag);
            if (b){
                return "{\"state\": 1,\"msg\": \"成功\"}";
            }else {
                return "{\"state\": 0,\"msg\": \"失败\"}";
            }
        }catch (Exception e){
            return "{\"state\": 0,\"msg\": \"失败\"}";
        }

    }


    /**
     * 得到历史数据
     * @return
     */
    @GetMapping("/history")
    public String getHistory(@RequestParam("tid") String tid, Model model){
        model.addAttribute("templateType","history-template");
        try {
            List<TemplateHistory> historyByTid = templateService.getHistoryByTid(Long.parseLong(tid));
            model.addAttribute("historyList",historyByTid);
            model.addAttribute("template",templateService.getTemplateById(Long.parseLong(tid)));
            return "panel";
        }catch (Exception e){
            log.error(e);
            return "500";
        }
    }

    @GetMapping("/useHistory")
    public String useHistory(@RequestParam("account") String account,
                             @RequestParam("tid") String tid,
                             @RequestParam("hid") String hid, Model model){
        try {
            long time = System.currentTimeMillis();
            User user = userService.getUserByAccount(account);
            TemplateHistory history = templateService.useTemplateHistory(Long.parseLong(tid), Long.parseLong(hid));
            if (history==null){
                return "404";
            }
            TemplateHistory history2 = templateService.getHistoryLastByTid(Long.parseLong(tid));
            if (history2==null||history2.getContent()==null||history2.getContent().equals(history.getContent())){
                history.setModifiedTime(time);
                templateService.addHistory(history);
            }
            Template template = templateService.getTemplateById(Long.parseLong(tid));
            template.setModifiedTime(time);
            templateService.updateTemplate(template);
            String md5 = Md5Util.md5(String.format("account=%s&tid=%s&time=%s",user.getAccount(),template.getTid(),time));
            return "redirect:/user/main?account="+user.getAccount()+"&tid="+template.getTid()+"&time="+time+"&sign="+md5;
        }catch (Exception e){
            log.error(e);
            return "500";
        }
    }

    @GetMapping("/history/results")
    public String getHistory(@RequestParam("account") String account,
                             @RequestParam("tid") String tid,
                             @RequestParam("hid") String hid, Model model){
        if(StringUtils.isEmpty(hid)){
            return "404";
        }
        try {
            TemplateResult result = templateService.getResultById(Long.parseLong(hid));
            if (result==null){
                model.addAttribute("templateType","result");
                model.addAttribute("titles",new ArrayList());
                model.addAttribute("list",new ArrayList());
            }else {
                List<Map> res = JSON.parseObject(result.getResult(),new TypeReference<List<Map>>(){}.getType());
                if (res!=null&&res.size()>0){
                    model.addAttribute("templateType","result");
                    model.addAttribute("titles",res.get(0).keySet());
                    model.addAttribute("list",res);
                }else {
                    model.addAttribute("templateType","result");
                    model.addAttribute("titles",new ArrayList());
                    model.addAttribute("list",new ArrayList());
                }
            }
        }catch (Exception e){
            log.error(e);
        }
        return "panel";
    }

}
