package ZPO.Project.Services;

import ZPO.Project.Entities.Odpowiedz;
import ZPO.Project.Entities.Pytanie;
import ZPO.Project.Models.AnswerModel;
import ZPO.Project.Repositories.OdpowiedzRepository;
import ZPO.Project.Repositories.PytanieRepository;
import org.hibernate.query.sqm.tree.predicate.AbstractNegatableSqmPredicate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdpowiedzService {
    private final OdpowiedzRepository odpowiedzRepository;

    public OdpowiedzService(OdpowiedzRepository odpowiedzRepository) {
        this.odpowiedzRepository = odpowiedzRepository;
    }
    public List<Odpowiedz> getAllAnswersForQuestionSortedByDate(Long questionId) {
        return odpowiedzRepository.findByPytanieIdOrderByDataUtworzeniaAsc(questionId);
    }
    public Odpowiedz createOdpowiedz(Long questionId, AnswerModel answerModel) {
        // Ustawienie pytania na podstawie ID
        Pytanie question = new Pytanie();
        question.setId(questionId);
        Odpowiedz odpowiedz = new Odpowiedz(answerModel);
        odpowiedz.setPytanie(question);

        // Zapisanie odpowiedzi
        odpowiedzRepository.save(odpowiedz);

        return odpowiedz;
    }
}
