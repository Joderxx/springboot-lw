package springboot.lw.core.util;

import org.springframework.util.DigestUtils;

public class Md5Util {

    public static String md5(String s){
        return DigestUtils.md5DigestAsHex(s.getBytes());
    }
}
