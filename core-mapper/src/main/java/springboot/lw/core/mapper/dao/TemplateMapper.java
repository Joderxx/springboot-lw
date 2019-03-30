package springboot.lw.core.mapper.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @CachePut(value = "template",key = "#template.tid")
    int add(Template template);

    /**
     * 删除
     * @param userId
     * @param tid
     * @return
     */
    @CacheEvict(value = "template",key = "#tid")
    int deleteById(@Param("userId") long userId, @Param("tid") long tid);

    /**
     * 获取公开模板
     * @param uid
     * @return
     */
    Template getPublicById(long uid);

    /**
     * 获取模板
     * @param tid
     * @return
     */
    @Cacheable(value = "template",key = "#tid")
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
