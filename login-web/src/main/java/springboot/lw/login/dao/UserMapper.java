package springboot.lw.login.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import springboot.lw.core.model.User;

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
}
