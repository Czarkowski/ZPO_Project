package ZPO.Project.Services;

import ZPO.Project.Entities.Oferta;
import ZPO.Project.Entities.Pytanie;
import ZPO.Project.Models.QuestionModel;
import ZPO.Project.Repositories.OfertaRepository;
import ZPO.Project.Repositories.PytanieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PytanieService {
    private final PytanieRepository pytanieRepository;

    public PytanieService(PytanieRepository pytanieRepository) {
        this.pytanieRepository = pytanieRepository;
    }

    public List<Pytanie> getAllPytania() {
        return pytanieRepository.findAll();
    }

    public Pytanie createPytanie(Pytanie pytanie) {
        return pytanieRepository.save(pytanie);
    }
    public Pytanie createPytanie(QuestionModel questionModel) {
        var pytanie = new Pytanie(questionModel);

        return pytanieRepository.save(pytanie);
    }
    // Dodaj inne metody serwisu
}
