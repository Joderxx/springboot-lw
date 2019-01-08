package springboot.lw.login.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import springboot.lw.core.model.UserImage;

import java.util.List;


@Mapper
@Repository
public interface ImageMapper {

    int save(UserImage userImage);

    int update(UserImage userImage);

    List<UserImage> getUserImage(String account);

    UserImage getLastUserImage(String account);

}
