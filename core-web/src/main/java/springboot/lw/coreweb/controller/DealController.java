package springboot.lw.coreweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springboot.lw.core.model.ExcuteParameter;
import springboot.lw.core.model.Result;
import springboot.lw.core.service.Excute;

/**
 * @author xiejiedun on 2019/1/5
 */
@Controller
@RequestMapping("/user/crawl")
public class DealController {

    @Autowired
    private Excute excute;

    @PostMapping("/doProcess")
    public String doProcess(String method, String url, Model model) throws Exception {
        ExcuteParameter parameter = new ExcuteParameter();
        parameter.setUrl(url);
        parameter.setMethod(method);
        Result excute = this.excute.excute(parameter);
        model.addAttribute("result",excute);
        model.addAttribute("url",url);
        model.addAttribute("method",method);
        return "panel";
    }
}
