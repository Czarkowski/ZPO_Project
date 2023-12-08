package ZPO.Project.Entities;

import ZPO.Project.PayPal.PayPalOrderDetailsJson;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class Platnosc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String payPalId;
    private String payerId;
    private String status;

    @OneToOne
    @JoinColumn(name = "Zamowienie_id")
    private Zamowienie zamowienie;

    private Date dataAktualizacji;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataUtworzenia;

    public Platnosc() {

    }

    @PrePersist
    protected void onCreate() {
        dataUtworzenia = new Date();
    }

    public Platnosc(Zamowienie zamowienie, PayPalOrderDetailsJson payPalOrderDetailsJson){
        this.zamowienie = zamowienie;
        payPalId = payPalOrderDetailsJson.id;
        status = payPalOrderDetailsJson.status;
    }
    public void UpdateStan(PayPalOrderDetailsJson payPalOrderDetailsJson){
        dataAktualizacji = new Date();
        status = payPalOrderDetailsJson.status;
        payerId = payPalOrderDetailsJson.payer.payer_id;
    }
}