package ZPO.Project.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Page1Controller {

    @GetMapping("/page1")
    public String page1() {
        return "page1";
    }
}