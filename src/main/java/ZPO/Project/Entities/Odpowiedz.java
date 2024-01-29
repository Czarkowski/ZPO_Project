package ZPO.Project.Entities;

import ZPO.Project.Models.AnswerModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Odpowiedz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 10, max = 1023)
    private String tresc;

    @Size(min = 3, max = 255)
    private String uzytkownik;

    @ManyToOne
    @JoinColumn(name = "pytanie_id")
    private Pytanie pytanie;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataUtworzenia;

    public Odpowiedz(AnswerModel answerModel) {
        this.setTresc(answerModel.getTresc());
        this.setUzytkownik(answerModel.getUzytkownik());
    }
    public Odpowiedz(){}

    @PrePersist
    protected void onCreate() {
        dataUtworzenia = new Date();
    }
}