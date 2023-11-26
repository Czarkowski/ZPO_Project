package ZPO.Project.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Oferta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nazwa;



    @OneToMany(mappedBy = "oferta", cascade = CascadeType.ALL)
    private List<Cena> ceny;

    @OneToMany(mappedBy = "oferta", cascade = CascadeType.ALL)
    private List<Opis> opisy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataUtworzenia;
    @PrePersist
    protected void onCreate() {
        dataUtworzenia = new Date();
    }

    public Cena GetCurrentCena(){
        ceny.sort(Comparator.comparing(Cena::getDataUtworzenia).reversed());
        List<Cena> najnowszaCenaList = ceny;
        Cena cena = null;
        if (!najnowszaCenaList.isEmpty()) {
            Cena najnowszaCena = najnowszaCenaList.get(0);
            cena = najnowszaCena;
        } else {
        }
        return cena;
    }
    public Opis GetCurrentOpis(){
        opisy.sort(Comparator.comparing(Opis::getDataUtworzenia).reversed());
        List<Opis> najnowszaList = opisy;
        Opis ret = null;
        if (!najnowszaList.isEmpty()) {
            Opis najnowsza = najnowszaList.get(0);
            ret = najnowsza;
        } else {
        }
        return ret;
    }
}
