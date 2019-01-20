package springboot.lw.core.service;

import springboot.lw.core.model.Template;

import java.util.List;

/**
 * @author xiejiedun on 2019/1/17
 */
public interface TemplateService {

    boolean save(Template template);

    List<Template> getUserTemplate(long userId);

    Template getTemplateById(long tid);


}
