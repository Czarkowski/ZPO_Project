package ZPO.Project.Controllers;

import ZPO.Project.Routing.StaticRoutesName;
import ZPO.Project.Services.ZamowienieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PaymentController {

    @Autowired
    private ZamowienieService zamowienieService ;

    @GetMapping(StaticRoutesName.PAYMENT)
    public String shop(Model model, @PathVariable Long orderId) {
        var zam = zamowienieService.GetZamowienieById(orderId);
        System.out.println(zam);
        // Tutaj możesz dodać logikę pobierania danych o produktach miodu z serwisu
        // i przekazać je do widoku za pomocą modelu
        return "payment";
    }
}
