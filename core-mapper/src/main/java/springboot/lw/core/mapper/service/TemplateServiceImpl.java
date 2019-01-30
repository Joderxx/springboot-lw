package springboot.lw.core.mapper.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springboot.lw.core.mapper.dao.TemplateHistoryMapper;
import springboot.lw.core.mapper.dao.TemplateMapper;
import springboot.lw.core.mapper.dao.TemplateResultMapper;
import springboot.lw.core.model.Template;
import springboot.lw.core.model.TemplateHistory;
import springboot.lw.core.model.TemplateResult;
import springboot.lw.core.model.User;
import springboot.lw.core.service.TemplateService;

import java.util.List;
import java.util.Map;

@Service(interfaceClass = TemplateService.class)
@Component
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateMapper templateMapper;
    @Autowired
    private TemplateHistoryMapper templateHistoryMapper;
    @Autowired
    private TemplateResultMapper templateResultMapper;

    @Override
    public boolean saveTemplate(Template template) {
        return templateMapper.add(template)>0;
    }

    @Override
    public boolean updateTemplatePublish(long tid,boolean publish) {
        Template template = new Template();
        template.setTid(tid);
        template.setPublish(publish);
        return templateMapper.update(template)>0;
    }

    @Override
    public boolean updateTemplate(Template template) {
        return templateMapper.update(template)>0;
    }

    @Override
    public List<Template> getUserTemplate(long userId) {
        return templateMapper.getUserTemplates(userId);
    }

    @Override
    public Template getTemplateById(long tid) {
        return templateMapper.getById(tid);
    }

    @Override
    public Template getTemplateDetailById(long tid) {
        Map<String, Object> lastEdit = templateMapper.getDetailById(tid);
        //tid,`name`,description,t.create_time,t.modified_time,publish,username,u.id,success,content
        Template template = new Template();
        template.setTid(Long.parseLong(String.valueOf(lastEdit.get("tid"))));
        template.setName(String.valueOf(lastEdit.get("name")));
        template.setDescription(String.valueOf(lastEdit.get("description")));
        template.setCreateTime(Long.parseLong(String.valueOf(lastEdit.get("create_time"))));
        template.setModifiedTime(Long.parseLong(String.valueOf(lastEdit.get("modified_time"))));
        template.setPublish(Boolean.parseBoolean(String.valueOf(lastEdit.get("publish"))));
        User user = new User();
        user.setId(Long.parseLong(String.valueOf(lastEdit.get("id"))));
        user.setUsername(lastEdit.get("username").toString());
        template.setUser(user);
        TemplateHistory history = new TemplateHistory();
        history.setSuccess(Integer.parseInt(String.valueOf(lastEdit.get("success")==null?"0":lastEdit.get("success"))));
        history.setContent(String.valueOf(lastEdit.get("content")));
        template.setHistory(history);
        return template;
    }

    @Override
    public Template getTemplateByUidAndTid(long userId, long tid) {
        return templateMapper.getByUserIdAndId(userId,tid);
    }

    @Override
    public List<Template> getPublicTemplate() {
        return templateMapper.getPublicTemplate();
    }

    @Override
    public Template getTemplateLastEdit(long userId) {
        Map<String, Object> lastEdit = templateMapper.getLastEdit(userId);
        //tid,`name`,description,t.create_time,t.modified_time,publish,username,u.id,success,content
        Template template = new Template();
        template.setTid(Long.parseLong(String.valueOf(lastEdit.get("tid"))));
        template.setName(String.valueOf(lastEdit.get("name")));
        template.setDescription(String.valueOf(lastEdit.get("description")));
        template.setCreateTime(Long.parseLong(String.valueOf(lastEdit.get("create_time"))));
        template.setModifiedTime(Long.parseLong(String.valueOf(lastEdit.get("modified_time"))));
        template.setPublish(Boolean.parseBoolean(String.valueOf(lastEdit.get("publish"))));
        User user = new User();
        user.setId(Long.parseLong(String.valueOf(lastEdit.get("id"))));
        user.setUsername(lastEdit.get("username").toString());
        template.setUser(user);
        TemplateHistory history = new TemplateHistory();
        history.setSuccess(Integer.parseInt(String.valueOf(lastEdit.get("success")==null?"0":lastEdit.get("success"))));
        history.setContent(String.valueOf(lastEdit.get("content")==null?"":lastEdit.get("content")));
        template.setHistory(history);
        return template;
    }

    @Override
    public List<Map<String, Object>> getUserTemplates(long userId) {
        return templateMapper.getTemplateByUId(userId);
    }

    @Override
    public boolean addResult(TemplateResult result) {
        return templateResultMapper.add(result)>0;
    }


    @Override
    public TemplateResult getResultById(long hid) {
        return templateResultMapper.getById(hid);
    }

    @Override
    public TemplateResult getResultLastSuccess(long tid) {
        return templateResultMapper.getLastSuccess(tid);
    }

    @Override
    public TemplateResult getResultLast(long tid) {
        return templateResultMapper.getLastSuccess(tid);
    }

    @Override
    public boolean addHistory(TemplateHistory history) {
        return templateHistoryMapper.add(history)>0;
    }

    @Override
    public List<TemplateHistory> getHistoryByTid(long tid) {
        return templateHistoryMapper.getByTid(tid);
    }

    @Override
    public TemplateHistory getHistorySuccessByTid(long tid) {
        return templateHistoryMapper.getLastSuccess(tid);
    }

    @Override
    public TemplateHistory getHistoryLastByTid(long tid) {
        return templateHistoryMapper.getLast(tid);
    }

    @Override
    public TemplateHistory useTemplateHistory(long tid, long hid) {
        return templateHistoryMapper.getByTidAndHid(tid,hid);
    }
    @Override
    public boolean updateHistory(TemplateHistory templateHistory) {
        return templateHistoryMapper.update(templateHistory)>0;
    }
}
