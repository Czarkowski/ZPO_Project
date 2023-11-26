package ZPO.Project.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Cena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double wartosc;
    // Inne pola

    @ManyToOne
    @JoinColumn(name = "oferta_id")
    private Oferta oferta;


    @Temporal(TemporalType.TIMESTAMP)
    private Date dataUtworzenia;
    @PrePersist
    protected void onCreate() {
        dataUtworzenia = new Date();
    }
    // Getters, setters i inne metody
}