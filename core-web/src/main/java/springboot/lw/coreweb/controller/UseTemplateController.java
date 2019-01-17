package springboot.lw.coreweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xiejiedun on 2019/1/16
 */
@Controller
@RequestMapping("/templates")
public class UseTemplateController extends BaseController {

    /**
     * 运用某个模板
     * @return
     */
    @GetMapping("/use/{id}")
    public String useTemplate(){
        return "";
    }
}
