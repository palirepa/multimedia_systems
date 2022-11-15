package mds.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("static_page")
    public String staticPage(){
        return "staticpage";
    }

    @GetMapping("dynamic_page")
    public String dynamicPage(Model model){
        model.addAttribute("name", "Pali");
        model.addAttribute("id", "230632");
        return "dynamicpage";
    }
//------------------------------------------------------------------------
    @GetMapping("myself")
    public String myself(){
        return "myself";
    }

    @GetMapping("alice")
    public String alice(Model model){
        model.addAttribute("name", "Alice");
        return "template";
    }

    @GetMapping("bob")
    public String bob(Model model){
        model.addAttribute("name", "Bob");
        return "template";
    }
}
