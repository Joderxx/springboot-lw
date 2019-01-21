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
import springboot.lw.core.service.TemplateService;

import java.util.List;

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
    public List<Template> getUserTemplate(long userId) {
        return templateMapper.getUserTemplates(userId);
    }

    @Override
    public Template getTemplateById(long tid) {
        return templateMapper.getById(tid);
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
        return false;
    }
}
