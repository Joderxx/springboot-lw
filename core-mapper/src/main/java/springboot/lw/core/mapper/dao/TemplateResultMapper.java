package springboot.lw.core.mapper.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import springboot.lw.core.model.TemplateResult;

/**
 * @author xiejiedun on 2019/1/17
 */
@Mapper
@Repository
public interface TemplateResultMapper {

    @CachePut(value = "templateResult",key = "#result.hid")
    int add(TemplateResult result);

    @CachePut(value = "templateResult",key = "#result.hid")
    int update(TemplateResult result);

    @Cacheable(value = "templateResult",key = "#result.hid")
    TemplateResult getById(long hid);

    TemplateResult getLastSuccess(long tId);

    TemplateResult getLast(long tId);
}
