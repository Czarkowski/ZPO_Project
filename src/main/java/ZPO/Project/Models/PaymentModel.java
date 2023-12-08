package ZPO.Project.Models;

import ZPO.Project.Entities.Pozycja;
import ZPO.Project.Entities.Zamowienie;
import ZPO.Project.Services.PasiekaService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class PaymentModel {

    public String name;
    public String surname;
    public String email;
    public Long orderId;
    public List<Item> productList;
    public BigDecimal totalPrice;

    public PaymentModel(Zamowienie zamowienie, PasiekaService pasiekaService){
        name = zamowienie.getImie();
        email = zamowienie.getEmail();
        surname = zamowienie.getNazwisko();
        orderId = zamowienie.getId();
        productList = zamowienie.getPozycje().stream().map(pozycja -> new Item(pozycja, pasiekaService)).toList();
        totalPrice = productList.stream().map(item -> item.getTotalPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Data
    @NoArgsConstructor
    public class Item{

        private String name;
        private BigDecimal price;
        private BigDecimal totalPrice;
        private int quantity;

        public Item(Pozycja pozycja, PasiekaService pasiekaService){
            name = pozycja.getName();
//            price = pasiekaService.GetPriceByPriceId(pozycja.getCenaId());
            price = pozycja.getCenaValue();
            quantity = pozycja.getIlosc();
            totalPrice = price.multiply(BigDecimal.valueOf(quantity));
        }
    }
}
