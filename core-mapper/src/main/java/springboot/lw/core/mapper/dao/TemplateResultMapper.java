package springboot.lw.core.mapper.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import springboot.lw.core.model.TemplateResult;

/**
 * @author xiejiedun on 2019/1/17
 */
@Mapper
@Repository
public interface TemplateResultMapper {

    int add(TemplateResult result);

    int update(TemplateResult result);

    TemplateResult getById(long hid);

    TemplateResult getLastSuccess(long tId);

    TemplateResult getLast(long tId);
}
