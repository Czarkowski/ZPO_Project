package ZPO.Project.Models;

import ZPO.Project.Entities.Cena;
import ZPO.Project.Entities.Oferta;
import ZPO.Project.Entities.Opis;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long cenaId;
    private Long opisId;
    private String nazwa;
    private BigDecimal cena;
    private String opis;
    private int ilosc = 1;

    public static List<Product> GetProductsFromOferta(List<Oferta> ofertaList){
        List<Product> ret = new ArrayList<>();
        ofertaList.forEach(oferta -> {
            Cena c = oferta.GetCurrentCena();
            Opis o = oferta.GetCurrentOpis();
            Product produkt = new Product();
            produkt.setId(oferta.getId());
            produkt.setNazwa(oferta.getNazwa());
            produkt.setCena(c.getWartosc());
            produkt.setCenaId(c.getId());
            produkt.setOpis(o.getText());
            produkt.setOpisId(o.getId());
            produkt.setIlosc(1);
            ret.add(produkt);
        });
        return ret;
    }
}