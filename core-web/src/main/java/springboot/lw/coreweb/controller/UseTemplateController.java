package springboot.lw.coreweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xiejiedun on 2019/1/16
 */
@Controller
public class UseTemplateController extends BaseController {

    /**
     * 运用某个模板
     * @return
     */
    @GetMapping("")
    public String useTemplate(){
        return "";
    }
}
