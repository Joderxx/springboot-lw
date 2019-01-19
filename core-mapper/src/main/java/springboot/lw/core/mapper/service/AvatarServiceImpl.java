package springboot.lw.core.mapper.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springboot.lw.core.mapper.dao.ImageMapper;
import springboot.lw.core.model.UserImage;
import springboot.lw.core.service.AvatarService;

/**
 * @author xiejiedun on 2019/1/17
 */
@Component
@Service(interfaceClass = AvatarService.class)
public class AvatarServiceImpl implements AvatarService {

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public boolean saveAvatar(byte[] data,String account){
        UserImage image = new UserImage();
        image.setImage(data);
        image.setAccount(account);
        image.setMode("insert");
        long time = System.currentTimeMillis();
        image.setCreateTime(time);
        image.setModifiedTime(time);
        int num = imageMapper.save(image);
        return num>0;
    }

    @Override
    public UserImage getAvatar(String account){
        return imageMapper.getLastUserImage(account);
    }
}
