package ZPO.Project.RestApiController;

import ZPO.Project.Models.Product;
import ZPO.Project.Routing.APIRoutesName;
import ZPO.Project.Session.SessionController;
import ZPO.Project.Session.ShoppingCart;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIRoutesName.PREFIX)
public class CartRestApiController {

    @PostMapping(APIRoutesName.CART)
    public ResponseEntity<String> addProductToCart(@RequestBody Product product, HttpSession httpSession) {
        ShoppingCart shoppingCart = SessionController.SafeGetObject(httpSession, SessionController.cartName);
        String response;
        var op = shoppingCart.getProducts().stream().filter(x -> x.getId().equals(product.getId())).findFirst();
        if (op.isPresent()){
            var p = op.get();
            if (product.getIlosc() < 1){
                shoppingCart.removeProduct(p);
                System.out.println("Remove product from cart. " + shoppingCart.getProducts().size());
                response = "Remove product from cart.";
            }
            p.setIlosc(product.getIlosc());
            System.out.println("Product is in cart.");
            response =  "Product is in cart.";
        }else {
            shoppingCart.addProduct(product);
            httpSession.setAttribute(SessionController.cartName, shoppingCart);
            System.out.println("Product added to the cart." + shoppingCart.getProducts());
            response = "Product added to the cart.";
        }
        SessionController.SafeSetObject(httpSession, SessionController.cartName, shoppingCart);
        return ResponseEntity.ok(response);
    }

    @GetMapping(APIRoutesName.CART)
    public ResponseEntity<List<Product>> getCartProducts(HttpSession httpSession) {
        ShoppingCart shoppingCart = SessionController.SafeGetObject(httpSession, SessionController.cartName);
        List<Product> products = shoppingCart.getProducts();
        System.out.println(products);
        return ResponseEntity.ok(products);
    }
}