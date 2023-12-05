package ZPO.Project.Controllers;

import ZPO.Project.Routing.StaticRoutesName;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShopController {


    @GetMapping(StaticRoutesName.SHOP)
    public String shop(Model model) {
        return "shop";
    }
}
