package springboot.lw.core.mapper.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import springboot.lw.core.model.TemplateHistory;

import java.util.List;

/**
 * @author xiejiedun on 2019/1/17
 */
@Mapper
@Repository
public interface TemplateHistoryMapper {

    @CachePut(value = "templateHistory",key = "#history.hid")
    int add(TemplateHistory history);

    @CachePut(value = "templateHistory",key = "#history.hid")
    int update(TemplateHistory history);

    List<TemplateHistory> getByTid(long tid);

    TemplateHistory getLast(long tid);

    TemplateHistory getLastSuccess(long tid);

    @Cacheable(value = "templateHistory",key = "#hid")
    TemplateHistory getById(long hid);

    TemplateHistory getByUserLast(long userId);

    TemplateHistory getByTidAndHid(@Param("tid") long tid, @Param("hid") long hid);

}
