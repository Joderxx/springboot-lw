package atest;

import com.alibaba.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import springboot.lw.core.service.UserService;
import springboot.lw.coreweb.CoreWebApplication;

/**
 * @author xiejiedun on 2019/1/17
 */
@SpringBootTest(classes = CoreWebApplication.class)
@RunWith(SpringRunner.class)
public class Atest {

    @Reference
    private UserService userService;

    @Test
    public void atest() {
        System.out.println(userService.getUserById(1));
    }
}
