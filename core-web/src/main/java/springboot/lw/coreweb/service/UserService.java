package springboot.lw.coreweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import springboot.lw.core.model.User;
import springboot.lw.coreweb.dao.UserMapper;


@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserByAccount(String account){
        if (StringUtils.isEmpty(account)){
            return null;
        }
        return userMapper.getUserByAccount(account);
    }
}
