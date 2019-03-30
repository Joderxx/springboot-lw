package springboot.lw.core.mapper.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import springboot.lw.core.model.UserImage;

import java.util.List;


@Mapper
@Repository
public interface ImageMapper {

    @CachePut(value = "userImage",key = "#userImage.account")
    int save(UserImage userImage);

    @CachePut(value = "userImage",key = "#userImage.account")
    int update(UserImage userImage);

    @Cacheable(value = "userImage",key = "#account")
    List<UserImage> getUserImage(String account);

    @Cacheable(value = "userImage",key = "#account")
    UserImage getLastUserImage(String account);

}
