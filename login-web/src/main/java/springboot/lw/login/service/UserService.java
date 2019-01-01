package springboot.lw.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.lw.login.dao.UserMapper;
import springboot.lw.login.model.User;
import springboot.lw.login.util.Md5Util;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getById(long userId){
        return userMapper.getById(userId);
    }

    public User getByAccountOrUsername(String s,String password){
        password = Md5Util.md5(password);
        User user = new User();
        user.setAccount(s);
        user.setUsername(s);
        user.setPassword(password);
        return userMapper.get(user);
    }

    public boolean updatePassword(long userId,String oldPassword,String newPassword){
        oldPassword = Md5Util.md5(oldPassword);
        newPassword = Md5Util.md5(newPassword);
        return userMapper.updatePassword(userId, oldPassword, newPassword)>0;
    }

    public boolean updateUserName(long userId,String newName){
        return userMapper.updateUsername(userId, newName)>0;
    }

    public Return insert(User user){
        if (userMapper.existAccount(user.getAccount())>0){
            return Return.REPEAT_ACCOUNT;
        }
        int num = userMapper.save(user);
        return num>0?Return.SUCCESS:Return.FAIL;
    }

    public enum Return{
        SUCCESS(0),FAIL(1),REPEAT_USERNAME(2),REPEAT_ACCOUNT(3);

        private int value;
        Return(int i){
            value = i;
        }

        public int getValue() {
            return value;
        }
    }
}
