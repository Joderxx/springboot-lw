package springboot.lw.coreweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot.lw.core.model.Template;
import springboot.lw.core.service.TemplateService;
import springboot.lw.core.util.Md5Util;
import springboot.lw.coreweb.dto.TemplateDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author xiejiedun on 2019/1/16
 * 公开模板
 */
@Controller
public class PublicController extends BaseController{

    @Reference
    private TemplateService templateService;

    /**
     * 获取所有公开模板
     * @return
     */
    @GetMapping({"/public","/user/public"})
    public String getAllPublicTemplate(HttpServletRequest request, Model model){
        model.addAttribute("templateType","public-template");
        List<Template> publicTemplate = templateService.getPublicTemplate();
        if (publicTemplate==null){
            model.addAttribute("templateList", Collections.EMPTY_LIST);
        }else {
            List<TemplateDTO> list = new ArrayList<>(publicTemplate.size());
            String account = request.getParameter("account");
            account = account==null?"":account;
            long time = System.currentTimeMillis();
            model.addAttribute("time",time);
            for (Template template:publicTemplate){
                TemplateDTO templateDTO = TemplateDTO.copy(template);
                templateDTO.setSign(Md5Util.md5(String.format("account=%s&tid=%s&time=%s",account,templateDTO.getTid(),time)));
                list.add(templateDTO);
            }
            model.addAttribute("templateList",list);
        }

        return "panel";
    }

}
