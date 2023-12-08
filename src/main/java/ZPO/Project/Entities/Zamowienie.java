package ZPO.Project.Entities;

import ZPO.Project.Models.OrderModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Zamowienie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imie;
    private String nazwisko;
    private String ulica;
    private String miejscowosc;
    private String kodPocztowy;
    private String email;
    private String tel;

    @OneToOne(mappedBy = "zamowienie")
    private Platnosc platnosc;

    @ToString.Exclude
    @OneToMany(mappedBy = "zamowienie",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Pozycja> pozycje;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataUtworzenia;

    public Zamowienie(OrderModel orderModel) {
        imie = orderModel.getName();
        nazwisko = orderModel.getSurname();
        ulica = orderModel.getStreet();
        miejscowosc = orderModel.getCity();
        kodPocztowy = orderModel.getPostalCode();
        email = orderModel.getEmail();
        tel = orderModel.getPhoneNumber();
    }

    public Zamowienie() {
    }

    @PrePersist
    protected void onCreate() {
        dataUtworzenia = new Date();
    }
}