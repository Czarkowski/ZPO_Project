package ZPO.Project.Models;

import ZPO.Project.Entities.Odpowiedz;
import ZPO.Project.Entities.Pytanie;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class AnswerModel {

    private Long id;

    private String tresc;
    private String uzytkownik;
    private Date dataUtworzenia;

    public AnswerModel(Odpowiedz odpowiedz) {
        this.setId(odpowiedz.getId());
        this.setUzytkownik(odpowiedz.getUzytkownik());
        this.setTresc(odpowiedz.getTresc());
        this.setDataUtworzenia(odpowiedz.getDataUtworzenia());
    }

    public static List<AnswerModel> GetAnswersFromOdpowiedzi(List<Odpowiedz> odpowiedzList){
        List<AnswerModel> ret = new ArrayList<>();
        odpowiedzList.forEach(odpowiedz -> {
            AnswerModel answer = new AnswerModel();
            answer.setId(odpowiedz.getId());
            answer.setUzytkownik(odpowiedz.getUzytkownik());
            answer.setTresc(odpowiedz.getTresc());
            answer.setDataUtworzenia(odpowiedz.getDataUtworzenia());
            ret.add(answer);
        });
        return ret;
    }
}
