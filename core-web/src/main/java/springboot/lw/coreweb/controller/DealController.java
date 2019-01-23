package springboot.lw.coreweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot.lw.core.model.ExcuteParameter;
import springboot.lw.core.model.RequestParam;
import springboot.lw.core.model.Result;
import springboot.lw.core.service.Excute;

/**
 * @author xiejiedun on 2019/1/5
 */
@Controller
@RequestMapping("/user/crawl")
public class DealController extends BaseController{

    @Reference
    private Excute excute;

    @PostMapping("/doProcess")
    public String doProcess(String method, String url, Model model) throws Exception {
        ExcuteParameter parameter = new ExcuteParameter();
        RequestParam requestParam = new RequestParam();
        requestParam.setUrl(url);
        requestParam.setMethod(method);
        parameter.setRequestParam(requestParam);
        Result excute = this.excute.excute(parameter);
        model.addAttribute("result",excute);
        model.addAttribute("url",url);
        model.addAttribute("method",method);
        return "panel";
    }
}
