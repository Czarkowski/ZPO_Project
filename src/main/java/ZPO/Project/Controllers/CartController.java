package ZPO.Project.Controllers;

import ZPO.Project.Routing.StaticRoutesName;
import ZPO.Project.Session.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

    private final ShoppingCart shoppingCart; // Załóżmy, że masz dostęp do instancji koszyka

    public CartController(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
//
//    @GetMapping(StaticRoutesName.CART)
//    public String viewCart(Model model) {
//        model.addAttribute("cartItems", shoppingCart.getProducts());
//        return "cart";
//    }
}
