package ZPO.Project.Entities;

import ZPO.Project.Models.Product;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Entity
@Data
@ToString(exclude = "zamowienie")
public class Pozycja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ofertaId;
    private Long cenaId;
    private Long opisId;

    @ManyToOne
    @JoinColumn(name = "zamowienie_id")
    private Zamowienie zamowienie;

    public Pozycja(Product product, Zamowienie zamowienie){
        ofertaId = product.getId();
        cenaId = product.getCenaId();
        opisId = product.getOpisId();
        this.zamowienie = zamowienie;
    }

    public Pozycja() {
    }
}