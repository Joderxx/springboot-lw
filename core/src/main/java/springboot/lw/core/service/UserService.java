package springboot.lw.core.service;

import springboot.lw.core.model.User;
import springboot.lw.core.model.enumeration.Return;
import springboot.lw.core.util.Md5Util;

/**
 * @author xiejiedun on 2019/1/17
 */

public interface UserService {

    User getUserByAccount(String account);

    User getUserById(long userId);


    User getByAccountOrUsername(String s,String password);

    boolean updatePassword(long userId,String oldPassword,String newPassword);

    boolean updateUserName(long userId,String newName);

    Return insert(User user);
}
