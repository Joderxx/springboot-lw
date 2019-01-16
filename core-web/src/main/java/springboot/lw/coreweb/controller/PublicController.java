package springboot.lw.coreweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author xiejiedun on 2019/1/16
 * 公开模板
 */
@Controller
public class PublicController extends BaseController{

    /**
     * 获取所有公开模板
     * @return
     */
    @GetMapping("")
    public String getAllPublicTemplate(){
        return "";
    }

}
