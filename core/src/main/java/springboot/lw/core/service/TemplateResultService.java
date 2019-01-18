package springboot.lw.core.service;

import springboot.lw.core.model.TemplateResult;

import java.util.List;

/**
 * @author xiejiedun on 2019/1/17
 */
public interface TemplateResultService {

    /**
     * 添加
     * @param result
     * @return
     */
    int add(TemplateResult result);

    /**
     * 获取历史结果列表
     * @param tid
     * @return
     */
    List<TemplateResult> getByTId(long tid);

    /**
     * 获取某个结果
     * @param tid
     * @return
     */
    TemplateResult getById(long tid);

    /**
     * 获取最后成功结果
     * @param tid
     * @return
     */
    TemplateResult getLastSuccess(long tid);

    /**
     * 获取最后结果
     * @param tid
     * @return
     */
    TemplateResult getLast(long tid);
}
