package springboot.lw.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springboot.lw.core.model.UserImage;
import springboot.lw.login.config.ParamConfig;
import springboot.lw.login.service.AvatarService;
import springboot.lw.core.util.Md5Util;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@CrossOrigin
@Controller
@RequestMapping("/user")
public class AvatarController {

    @Autowired
    private ParamConfig paramConfig;

    @Autowired
    private AvatarService avatarService;

    @GetMapping("/avatar")
    public void getAvatar(@RequestParam(value = "account",defaultValue = "") String account,
                          @RequestParam(value = "time",defaultValue = "0") long time,
                          @RequestParam(value = "sign",defaultValue = "") String sign,
                          HttpServletResponse response){
        byte[] data = null;
        if (StringUtils.isEmpty(sign)||StringUtils.isEmpty(account)||time==0){
            data = getDefault();
        }
        //半小时
        else if (System.currentTimeMillis()-time>1800000){
            data = getDefault();
        }
        String md5 = Md5Util.md5("account="+account+"&time="+time);
        if (!md5.equals(sign)){
            data = getDefault();
        }
        if (data ==null){
            UserImage image = avatarService.getAvatar(account);
            if (image==null){
                data = getDefault();
            }else {
                data = image.getImage();
            }
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
                               @RequestParam(value = "time",defaultValue = "0") long time,
                               @RequestParam(value = "sign",defaultValue = "") String sign,
                             @RequestParam("reportFile") MultipartFile file){
        if (StringUtils.isEmpty(sign)||StringUtils.isEmpty(account)||time==0){
            return "0";
        }
        //半小时
        else if (System.currentTimeMillis()-time>1800000){
            return "0";
        }
        String md5 = Md5Util.md5("account="+account+"&time="+time);
        if (!md5.equals(sign)){
            return "0";
        }

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
        File file = new File(path,paramConfig.getProperty("default.avatar"));
        try {
            return FileCopyUtils.copyToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
