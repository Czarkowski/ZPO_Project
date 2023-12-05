package ZPO.Project.Services;

import ZPO.Project.Entities.*;
import ZPO.Project.Models.AnswerModel;
import ZPO.Project.Models.OrderModel;
import ZPO.Project.Models.Product;
import ZPO.Project.Models.QuestionModel;
import ZPO.Project.Repositories.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PasiekaService {
    private final CenaRepository cenaRepository;
    private final OfertaRepository ofertaRepository;
    private final PozycjaRepository pozycjaRepository;
    private final ZamowienieRepository zamowienieRepository;
    private final PytanieRepository pytanieRepository;
    private final OdpowiedzRepository odpowiedzRepository;


    public PasiekaService(CenaRepository cenaRepository, OfertaRepository ofertaRepository, PozycjaRepository pozycjaRepository, ZamowienieRepository zamowienieRepository, PytanieRepository pytanieRepository, OdpowiedzRepository odpowiedzRepository) {
        this.cenaRepository = cenaRepository;
        this.ofertaRepository = ofertaRepository;
        this.pozycjaRepository = pozycjaRepository;
        this.zamowienieRepository = zamowienieRepository;
        this.pytanieRepository = pytanieRepository;
        this.odpowiedzRepository = odpowiedzRepository;
    }

    public List<Odpowiedz> getAllAnswersForQuestionSortedByDate(Long questionId) {
        return odpowiedzRepository.findByPytanieIdOrderByDataUtworzeniaAsc(questionId);
    }
    public Odpowiedz createOdpowiedz(Long questionId, AnswerModel answerModel) {
        Pytanie question = new Pytanie();
        question.setId(questionId);
        Odpowiedz odpowiedz = new Odpowiedz(answerModel);
        odpowiedz.setPytanie(question);
        odpowiedzRepository.save(odpowiedz);

        return odpowiedz;
    }
    public List<Oferta> getAllOferty() {
        return ofertaRepository.findAll();
    }


    public List<Pytanie> getAllPytania() {
        return pytanieRepository.findAll();
    }

    public Pytanie createPytanie(QuestionModel questionModel) {
        var pytanie = new Pytanie(questionModel);
        return pytanieRepository.save(pytanie);
    }

    public Zamowienie CreateZamowienie(OrderModel orderModel, List<Product> productList){
        var zam = new Zamowienie(orderModel);
        List<Pozycja> pozList = new ArrayList<Pozycja>();
        productList.forEach(product -> {
            var poz = new Pozycja(product, zam);
            pozList.add(poz);
            System.out.println(poz);
        });
        zam.setPozycje(pozList);
        zamowienieRepository.save(zam);
        return zam;
    }

    public Zamowienie GetZamowienieById(Long id){
        return zamowienieRepository.getReferenceById(id);
    }
}
