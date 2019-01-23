package springboot.lw.core.mapper.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import springboot.lw.core.model.Template;

import java.util.List;
import java.util.Map;

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
    int deleteById(@Param("userId") long userId, @Param("tid") long tid);

    /**
     * 获取公开模板
     * @param tid
     * @return
     */
    Template getPublicById(long tid);

    /**
     * 获取模板
     * @param tid
     * @return
     */
    Template getById(long tid);

    Map<String,Object> getDetailById(long tid);

    /**
     * 获取用户模板
     * @param userId
     * @param tid
     * @return
     */
    Template getByUserIdAndId(@Param("userId") long userId, @Param("tid") long tid);

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

    /**
     * 更新模板
     * @param template
     * @return
     */
    int update(Template template);

    Map<String,Object> getLastEdit(long userId);

    List<Map<String, Object>> getTemplateByUId(long userId);
}
