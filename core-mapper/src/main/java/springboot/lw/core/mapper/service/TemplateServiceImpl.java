package springboot.lw.core.mapper.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springboot.lw.core.mapper.dao.TemplateHistoryMapper;
import springboot.lw.core.mapper.dao.TemplateMapper;
import springboot.lw.core.mapper.dao.TemplateResultMapper;
import springboot.lw.core.model.Template;
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
    public boolean save(Template template) {
        return false;
    }

    @Override
    public List<Template> getUserTemplate(long userId) {
        return null;
    }

    @Override
    public Template getTemplateById(long tid) {
        return null;
    }
}
