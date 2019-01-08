package springboot.lw.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.lw.core.model.UserImage;
import springboot.lw.login.dao.ImageMapper;


@Service
public class AvatarService {

    @Autowired
    private ImageMapper imageMapper;

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

    public UserImage getAvatar(String account){
        return imageMapper.getLastUserImage(account);
    }
}
