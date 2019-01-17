package springboot.lw.core.service;

import springboot.lw.core.model.UserImage;

/**
 * @author xiejiedun on 2019/1/17
 */
public interface AvatarService {

    boolean saveAvatar(byte[] data,String account);

    UserImage getAvatar(String account);
}
