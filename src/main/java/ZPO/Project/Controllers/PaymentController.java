package ZPO.Project.Controllers;

import ZPO.Project.Routing.StaticRoutesName;
import ZPO.Project.Services.PasiekaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PaymentController {

    @Autowired
    private PasiekaService pasiekaService ;

    @GetMapping(StaticRoutesName.PAYMENT)
    public String shop(Model model, @PathVariable Long orderId) {
        var zam = pasiekaService.GetZamowienieById(orderId);
        System.out.println(zam);
        return "payment";
    }
}
