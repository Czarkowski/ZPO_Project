package ZPO.Project.Entities;

import ZPO.Project.Models.QuestionModel;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Pytanie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tresc;
    private String uzytkownik;
    private String tytul;

    @OneToMany(mappedBy = "pytanie", cascade = CascadeType.ALL)
    private List<Odpowiedz> odpowiedzi = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataUtworzenia;
    @PrePersist
    protected void onCreate() {
        dataUtworzenia = new Date();
    }

    public Pytanie(QuestionModel questionModel){
        this.setTresc(questionModel.getTresc());
        this.setTytul(questionModel.getTytul());
        this.setUzytkownik(questionModel.getUzytkownik());
    }
    public Pytanie(){}
}