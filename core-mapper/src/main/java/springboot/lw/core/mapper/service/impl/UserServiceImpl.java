package springboot.lw.core.mapper.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import springboot.lw.core.mapper.dao.UserMapper;
import springboot.lw.core.model.enumeration.Return;
import springboot.lw.core.service.UserService;
import springboot.lw.core.model.User;
import springboot.lw.core.util.Md5Util;

/**
 * @author xiejiedun on 2019/1/17
 */
@Component
@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByAccount(String account){
        if (StringUtils.isEmpty(account)){
            return null;
        }
        return userMapper.getUserByAccount(account);
    }

    @Override
    public User getUserById(long userId){
        return userMapper.getById(userId);
    }


    @Override
    public User getByAccountOrUsername(String s,String password){
        password = Md5Util.md5(password);
        User user = new User();
        user.setAccount(s);
        user.setUsername(s);
        user.setPassword(password);
        return userMapper.get(user);
    }

    @Override
    public boolean updatePassword(long userId,String oldPassword,String newPassword){
        oldPassword = Md5Util.md5(oldPassword);
        newPassword = Md5Util.md5(newPassword);
        return userMapper.updatePassword(userId, oldPassword, newPassword)>0;
    }

    @Override
    public boolean updateUserName(long userId,String newName){
        return userMapper.updateUsername(userId, newName)>0;
    }

    @Override
    public Return insert(User user){
        if (userMapper.existAccount(user.getAccount())>0){
            return Return.REPEAT_ACCOUNT;
        }
        int num = userMapper.save(user);
        return num>0?Return.SUCCESS:Return.FAIL;
    }
}
