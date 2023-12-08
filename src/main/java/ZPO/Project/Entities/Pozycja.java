package ZPO.Project.Entities;

import ZPO.Project.Models.Product;
import ZPO.Project.Services.PasiekaService;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
//@ToString(exclude = {"zamowienie","cena"})
public class Pozycja {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ofertaId;
//    private Long cenaId;
//    private double cena;

    @ManyToOne
    @JoinColumn(name = "cena_id")
    @ToString.Exclude
    private Cena cena;
    private Long opisId;
    private String name;
    private int ilosc;

    @ManyToOne
    @JoinColumn(name = "zamowienie_id")
    @ToString.Exclude
    private Zamowienie zamowienie;

    public Pozycja(Product product, Zamowienie zamowienie){
        ofertaId = product.getId();
        opisId = product.getOpisId();
        name = product.getNazwa();
        ilosc = product.getIlosc();
        cena = new Cena();
        cena.setId(product.getCenaId());
//        cenaId = product.getCenaId();
//        cena = product.getCena();
        this.zamowienie = zamowienie;
    }

    public BigDecimal getCenaValue(){
        return cena.getWartosc();
    }

    public Pozycja() {
    }
}