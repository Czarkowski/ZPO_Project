package ZPO.Project.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Platnosc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne
    @JoinColumn(name = "Zamowienie_id")
    private Zamowienie zamowienie;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataUtworzenia;
    @PrePersist
    protected void onCreate() {
        dataUtworzenia = new Date();
    }
}