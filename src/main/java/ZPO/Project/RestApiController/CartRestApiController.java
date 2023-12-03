package ZPO.Project.RestApiController;

import ZPO.Project.Models.Product;
import ZPO.Project.Routing.APIRoutesName;
import ZPO.Project.Session.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIRoutesName.CART_API)
public class CartRestApiController {

    @Autowired
    private ShoppingCart shoppingCart;

    @PostMapping(APIRoutesName.ADD)
    public ResponseEntity<String> addProductToCart(@RequestBody Product product) {
        var op = shoppingCart.getProducts().stream().filter(x -> x.getId().equals(product.getId())).findFirst();
        if (op.isPresent()){
            var p = op.get();
            if (product.getIlosc() < 1){
                shoppingCart.removeProduct(p);
                System.out.println("Remove product from cart. " + shoppingCart.getProducts().size());
                return ResponseEntity.ok("Remove product from cart.");
            }
            p.setIlosc(product.getIlosc());
            System.out.println("Product is in cart.");
            return ResponseEntity.ok("Product is in cart.");
        }else {
            shoppingCart.addProduct(product);
            System.out.println("Product added to the cart.");
            return ResponseEntity.ok("Product added to the cart.");
        }
    }

    @GetMapping(APIRoutesName.GET)
    public ResponseEntity<List<Product>> getCartProducts() {
        List<Product> products = shoppingCart.getProducts();
        System.out.println(products);
        return ResponseEntity.ok(products);
    }
}