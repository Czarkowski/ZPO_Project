package ZPO.Project.Models;

import ZPO.Project.Entities.Cena;
import ZPO.Project.Entities.Oferta;
import ZPO.Project.Entities.Opis;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Produkt {

    private Long id;
    private Long cenaId;
    private Long opisId;
    private String nazwa;
    private double cena;
    private String opis;

    public static List<Produkt> GetProductsFromOferta(List<Oferta> ofertaList){
        List<Produkt> ret = new ArrayList<>();
        ofertaList.forEach(oferta -> {
            Cena c = oferta.GetCurrentCena();
            Opis o = oferta.GetCurrentOpis();
            Produkt produkt = new Produkt();
            produkt.setId(oferta.getId());
            produkt.setNazwa(oferta.getNazwa());
            produkt.setCena(c.getWartosc());
            produkt.setCenaId(c.getId());
            produkt.setOpis(o.getText());
            produkt.setOpisId(o.getId());
            ret.add(produkt);
        });
        return ret;
    }
}