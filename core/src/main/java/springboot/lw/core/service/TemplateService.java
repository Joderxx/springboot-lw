package springboot.lw.core.service;

import springboot.lw.core.model.Template;
import springboot.lw.core.model.TemplateHistory;
import springboot.lw.core.model.TemplateResult;

import java.util.List;
import java.util.Map;

/**
 * @author xiejiedun on 2019/1/17
 */
public interface TemplateService {

    /**
     * 添加模板
     * @param template
     * @return
     */
    boolean saveTemplate(Template template);

    boolean updateTemplate(Template template);

    /**
     * 更新
     * @param tid
     * @param publish
     * @return
     */
    boolean updateTemplatePublish(long tid,boolean publish);

    List<Template> getPublicTemplate();

    /**
     * 得到用户模板
     * @param userId
     * @return
     */
    List<Template> getUserTemplate(long userId);

    /**
     * 得到模板
     * @param tid
     * @return
     */
    Template getTemplateById(long tid);

    Template getTemplateDetailById(long tid);

    /**
     * 得到用户模板
     * @param userId
     * @param tid
     * @return
     */
    Template getTemplateByUidAndTid(long userId,long tid);

    Template getTemplateLastEdit(long userId);

    List<Map<String, Object>> getUserTemplates(long userId);
    /**
     * 添加结果
     * @param result
     * @return
     */
    boolean addResult(TemplateResult result);

    boolean updateResult(TemplateResult result);
    /**
     * 获取某个结果
     * @param hid
     * @return
     */
    TemplateResult getResultById(long hid);

    /**
     * 获取最后成功结果
     * @param tid
     * @return
     */
    TemplateResult getResultLastSuccess(long tid);

    /**
     * 获取最后结果
     * @param tid
     * @return
     */
    TemplateResult getResultLast(long tid);

    boolean addHistory(TemplateHistory history);

    /**
     * 获得模板历史记录
     * @param tid
     * @return
     */
    List<TemplateHistory> getHistoryByTid(long tid);

    /**
     * 获得模板历史记录最后成功那个
     * @param tid
     * @return
     */
    TemplateHistory getHistorySuccessByTid(long tid);

    TemplateHistory getHistoryLastByTid(long tid);



    TemplateHistory useTemplateHistory(long tid,long hid);

    boolean updateHistory(TemplateHistory templateHistory);

}
