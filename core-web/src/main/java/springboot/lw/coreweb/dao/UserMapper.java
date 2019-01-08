package springboot.lw.coreweb.dao;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import springboot.lw.core.model.User;


@Mapper
@Repository
public interface UserMapper {

    User getUserByAccount(String account);
}
