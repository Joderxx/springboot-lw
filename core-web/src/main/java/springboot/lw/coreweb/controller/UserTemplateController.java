package springboot.lw.coreweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xiejiedun on 2019/1/16
 */
@Controller
@RequestMapping("/user/templates")
public class UserTemplateController extends BaseController {

    /**
     * 得到用户模板
     * @return
     */
    @GetMapping("/private")
    public String getTemplate(){
        return "";
    }

    /**
     * 公开模板
     * @return
     */
    @PostMapping("/publish")
    public String publicTemplate(){
        return "";
    }

    /**
     * 不公开公开模板
     * @return
     */
    @PostMapping("/unPublish")
    public String unPublicTemplate(){
        return "";
    }

    /**
     * 查看模板结果
     * @return
     */
    @GetMapping("/{id}/result")
    public String getResult(){
        return "";
    }

    /**
     * 得到历史数据
     * @return
     */
    @GetMapping("/{id}/history")
    public String getHistory(){
        return "";
    }

}
