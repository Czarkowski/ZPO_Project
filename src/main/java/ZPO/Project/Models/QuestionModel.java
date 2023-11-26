package ZPO.Project.Models;

import ZPO.Project.Entities.Pytanie;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class QuestionModel {

    private Long id;

    private String tresc;
    private String uzytkownik;
    private String tytul;
    private Date dataUtworzenia;

    public QuestionModel(Pytanie pytanie) {
        this.setId(pytanie.getId());
        this.setUzytkownik(pytanie.getUzytkownik());
        this.setTytul(pytanie.getTytul());
        this.setTresc(pytanie.getTresc());
        this.setDataUtworzenia(pytanie.getDataUtworzenia());
    }

    public static List<QuestionModel> GetQuestionsFromPytania(List<Pytanie> pytanieList){
        List<QuestionModel> ret = new ArrayList<>();
        pytanieList.forEach(pytanie -> {
            QuestionModel question = new QuestionModel();
            question.setId(pytanie.getId());
            question.setUzytkownik(pytanie.getUzytkownik());
            question.setTytul(pytanie.getTytul());
            question.setTresc(pytanie.getTresc());
            question.setDataUtworzenia(pytanie.getDataUtworzenia());
            ret.add(question);
        });
        return ret;
    }
}
