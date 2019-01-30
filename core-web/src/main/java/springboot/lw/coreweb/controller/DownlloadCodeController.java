package springboot.lw.coreweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springboot.lw.core.service.TemplateService;

/**
 * @author xiejiedun on 2019/1/29
 */
@Controller
@RequestMapping("/user/template")
public class DownlloadCodeController extends BaseController {

    @Reference
    private TemplateService templateService;

    @GetMapping("/downloadCode")
    public String downloadCode(@RequestParam(value = "tid",defaultValue = "0")String tid,
                               Model model){

        return "";
    }
}
