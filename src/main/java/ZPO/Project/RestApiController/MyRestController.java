package ZPO.Project.RestApiController;

import ZPO.Project.ApiModels.CalculationRequest;
import ZPO.Project.ApiModels.CalculationResponse;
import ZPO.Project.Entities.Odpowiedz;
import ZPO.Project.Entities.Pytanie;
import ZPO.Project.Models.AnswerModel;
import ZPO.Project.Models.Message;
import ZPO.Project.Models.Produkt;
import ZPO.Project.Models.QuestionModel;
import ZPO.Project.MyUtilities.MyStaticUtilities;
import ZPO.Project.Repositories.OfertaRepository;
import ZPO.Project.Routing.APIRoutesName;
import ZPO.Project.Services.MessageService;
import ZPO.Project.Services.OdpowiedzService;
import ZPO.Project.Services.OfertaService;
import ZPO.Project.Services.PytanieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(APIRoutesName.PREFIX)
public class MyRestController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private OfertaService ofertaService ;
    @Autowired
    private PytanieService pytanieService ;
    @Autowired
    private OdpowiedzService odpowiedzService ;

    @GetMapping("/message")
    public Message getMessage() {
        return messageService.generateMessage();
    }

    @PostMapping(APIRoutesName.CALCULATE)
    public ResponseEntity<CalculationResponse> calculate(@RequestBody CalculationRequest request) {
        CalculationResponse response = MyStaticUtilities.GetCalculationResponse(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping(APIRoutesName.OFFER)
    public ResponseEntity<List<Produkt>> oferta() {

        var oferta = ofertaService.getAllOferty();
        var produkty = Produkt.GetProductsFromOferta(oferta);
        return ResponseEntity.ok(produkty);
    }

    @GetMapping(APIRoutesName.ANSWER + APIRoutesName.PATHPARAM_QUESTIONID)
    public ResponseEntity<List<AnswerModel>> answer(@PathVariable Long questionId) {
        var odpowiedzi = odpowiedzService.getAllAnswersForQuestionSortedByDate(questionId);
        var answer = AnswerModel.GetAnswersFromOdpowiedzi(odpowiedzi);
        return ResponseEntity.ok(answer);
    }

    @PostMapping(APIRoutesName.QUESTION_ADD)
    public ResponseEntity<QuestionModel> addQuestion(@RequestBody QuestionModel question) {

        Pytanie savedQuestion = pytanieService.createPytanie(question);

        return ResponseEntity.ok(new QuestionModel(savedQuestion));
    }

    @PostMapping(APIRoutesName.ANSWER_ADD + APIRoutesName.PATHPARAM_QUESTIONID)
    public ResponseEntity<AnswerModel> addAnswer(@PathVariable Long questionId, @RequestBody AnswerModel answer) {
        Odpowiedz savedAnswer = odpowiedzService.createOdpowiedz(questionId, answer);
        return ResponseEntity.ok(new AnswerModel(savedAnswer));
    }
}