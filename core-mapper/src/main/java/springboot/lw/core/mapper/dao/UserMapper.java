package springboot.lw.core.mapper.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import springboot.lw.core.model.User;

/**
 * @author xiejiedun on 2019/1/17
 */
@Mapper
@Repository
public interface UserMapper {

    int update(User user);

    int save(User user);

    User getById(long userId);

    User get(User user);

    int updatePassword(@Param("userId") long userId,
                       @Param("oldPassword") String oldPassword,
                       @Param("newPassword") String newPassword);

    int updateUsername(@Param("userId") long userId,@Param("newName")String newName);

    int existUsername(String username);

    int existAccount(String account);

    @Cacheable(value = "user",key = "'user:'+#account")
    User getUserByAccount(String account);
}
