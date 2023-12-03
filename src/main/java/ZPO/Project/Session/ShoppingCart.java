package ZPO.Project.Session;

import ZPO.Project.Models.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart implements Serializable {

    public class Locker extends Object implements Serializable{};
    @Serial
    private static final long serialVersionUID = 1L;
    private final List<Product> products = new ArrayList<>();
    private final Locker lock = new Locker();

    public void addProduct(Product product) {
        synchronized (lock) {
            products.add(product);
        }
    }

    public void removeProduct(Product product) {
        synchronized (lock) {
            products.removeIf(p -> p.getId().equals(product.getId()));
        }
    }

    public List<Product> getProducts() {
        synchronized (lock) {
            return products;
        }
    }

    public void Clear() {
        products.clear();
    }
}

