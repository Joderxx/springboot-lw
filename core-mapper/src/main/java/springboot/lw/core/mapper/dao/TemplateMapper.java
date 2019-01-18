package springboot.lw.core.mapper.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import springboot.lw.core.model.Template;

import java.util.List;

/**
 * @author xiejiedun on 2019/1/17
 */
@Mapper
@Repository
public interface TemplateMapper {

    /**
     * 添加模板
     * @param template
     * @return
     */
    int add(Template template);

    /**
     * 删除
     * @param userId
     * @param tid
     * @return
     */
    int deleteById(long userId,long tid);

    /**
     * 获取公开模板
     * @param tid
     * @return
     */
    Template getById(long tid);

    /**
     * 获取用户模板
     * @param userId
     * @param tid
     * @return
     */
    Template getByUserIdAndId(long userId,long tid);

    /**
     * 获取用户模板列表
     * @param userId
     * @return
     */
    List<Template> getUserTemplates(long userId);

    /**
     * 获取公开模板
     * @return
     */
    List<Template> getPublicTemplate();
}
