package ZPO.Project.Controllers;

import ZPO.Project.Routing.StaticRoutesName;
import ZPO.Project.Session.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

    private final ShoppingCart shoppingCart;

    public CartController(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
