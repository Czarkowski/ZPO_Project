package ZPO.Project.RestApiController;

import ZPO.Project.ApiModels.CalculationRequest;
import ZPO.Project.ApiModels.CalculationResponse;
import ZPO.Project.Entities.Odpowiedz;
import ZPO.Project.Entities.Pytanie;
import ZPO.Project.Models.*;
import ZPO.Project.MyUtilities.MyStaticUtilities;
import ZPO.Project.Routing.APIRoutesName;
import ZPO.Project.Routing.StaticRoutesName;
import ZPO.Project.Services.*;
import ZPO.Project.Session.SessionController;
import ZPO.Project.Session.ShoppingCart;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(APIRoutesName.PREFIX)
public class MyRestController {

    @Autowired
    private PasiekaService pasiekaService;

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @PostMapping(APIRoutesName.QUESTION)
    public ResponseEntity<?> addQuestion(@RequestBody QuestionModel question) {
        Set<ConstraintViolation<QuestionModel>> violations = validator.validate(question);
        if (!violations.isEmpty()) {
            return ResponseEntity.badRequest().body(violations.toString());
        } else {
            Pytanie savedQuestion = pasiekaService.createPytanie(question);
            return ResponseEntity.ok(new QuestionModel(savedQuestion));
        }
    }

    @PostMapping(APIRoutesName.CALCULATE)
    public ResponseEntity<CalculationResponse> calculate(@RequestBody CalculationRequest request) {
        CalculationResponse response = MyStaticUtilities.GetCalculationResponse(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping(APIRoutesName.OFFER)
    public ResponseEntity<List<Product>> oferta() {

        var oferta = pasiekaService.getAllOferty();
        var produkty = Product.GetProductsFromOferta(oferta);
        return ResponseEntity.ok(produkty);
    }

    @GetMapping(APIRoutesName.ANSWER + APIRoutesName.PATHPARAM_QUESTIONID)
    public ResponseEntity<List<AnswerModel>> answer(@PathVariable Long questionId) {
        var odpowiedzi = pasiekaService.getAllAnswersForQuestionSortedByDate(questionId);
        var answer = AnswerModel.GetAnswersFromOdpowiedzi(odpowiedzi);
        return ResponseEntity.ok(answer);
    }

    @PostMapping(APIRoutesName.ANSWER + APIRoutesName.PATHPARAM_QUESTIONID)
    public ResponseEntity<AnswerModel> addAnswer(@PathVariable Long questionId, @RequestBody AnswerModel answer) {
        Odpowiedz savedAnswer = pasiekaService.createOdpowiedz(questionId, answer);
        return ResponseEntity.ok(new AnswerModel(savedAnswer));
    }

    //Zapisujemy zamowienie do bazy i zwracamy link do platnosci
    @PostMapping(APIRoutesName.ORDER)
    public ResponseEntity<CreateOrderResponse> CreateOrder(@RequestBody OrderModel orderModel, HttpSession httpSession) {
        ShoppingCart shoppingCart = SessionController.SafeGetObject(httpSession, SessionController.cartName);
        var zam = pasiekaService.CreateZamowienie(orderModel, shoppingCart.getProducts());
        httpSession.removeAttribute(SessionController.cartName);
        return ResponseEntity.ok(new CreateOrderResponse(StaticRoutesName.GetPaymentURL(zam.getId())));
    }
}
