package springboot.lw.coreweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import springboot.lw.core.model.Result;

@Controller
public class MainController {

    @GetMapping("/main")
    public String main(){
        return "panel";
    }
}
