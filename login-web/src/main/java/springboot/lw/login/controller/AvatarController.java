package springboot.lw.login.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springboot.lw.core.model.UserImage;
import springboot.lw.core.service.AvatarService;
import springboot.lw.login.config.ParamConfig;
import springboot.lw.core.util.Md5Util;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;

@CrossOrigin
@Controller
@RequestMapping("/user")
public class AvatarController {

    @Autowired
    private ParamConfig paramConfig;

    @Reference
    private AvatarService avatarService;

    @GetMapping("/avatar")
    public void getAvatar(@RequestParam(value = "account",defaultValue = "") String account,
                          @RequestParam(value = "time",defaultValue = "0") long time,
                          @RequestParam(value = "sign",defaultValue = "") String sign,
                          HttpServletResponse response){
        byte[] data = null;
        UserImage image = avatarService.getAvatar(account);
        if (image==null){
            data = getDefault();
        }else {
            data = image.getImage();
        }
        try {
            response.setContentType("image/jpeg");
            response.getOutputStream().write(data);
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @PostMapping("/avatar")
    public String updateAvatar(@RequestParam(value = "account",defaultValue = "") String account,
                               @RequestParam("reportFile") MultipartFile file){

        try {
            if (file.getBytes()!=null&&file.getBytes().length>0){
                boolean b = avatarService.saveAvatar(file.getBytes(),account);
                return b?"1":"0";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "0";
    }

    private byte[] getDefault(){
        String path = this.getClass().getResource("/static").getPath();
        try {
            path = URLDecoder.decode(path,"UTF-8");
            File file = new File(path,paramConfig.getProperty("default.avatar"));
            return FileCopyUtils.copyToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
