package springboot.lw.coreweb.dao;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import springboot.lw.core.model.User;


@Mapper
@Repository
@CacheConfig(cacheNames = "users")
public interface UserMapper {

    @Cacheable(value = "user",key = "'user:'+#account")
    User getUserByAccount(String account);
}
