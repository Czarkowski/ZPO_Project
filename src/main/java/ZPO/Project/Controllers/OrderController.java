package ZPO.Project.Controllers;

import ZPO.Project.Routing.StaticRoutesName;
import ZPO.Project.Session.ShoppingCart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {


    @GetMapping(StaticRoutesName.ORDER)
    public String order(Model model) {
        return "order";
    }
}