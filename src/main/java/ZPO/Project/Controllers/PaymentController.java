package ZPO.Project.Controllers;

import ZPO.Project.Entities.Platnosc;
import ZPO.Project.Models.PaymentModel;
import ZPO.Project.PayPal.PayPalConfigurationInfo;
import ZPO.Project.PayPal.PayPalUtilities;
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

        Platnosc platnosc = pasiekaService.GetPlatnoscByOrderId(orderId);
        if (platnosc != null && PayPalUtilities.CheckFinalizePayment(orderId, platnosc.getPayPalId(), new PayPalConfigurationInfo())) {
            var url =  "redirect:/finalize-order/" + orderId + "/good";
            System.out.println(url);
            return url;
        }
        var zamowienie = pasiekaService.GetZamowienieById(orderId);
        var paymentModel = new PaymentModel(zamowienie, pasiekaService);
        model.addAttribute("paymentModel",paymentModel);
        model.addAttribute("currency", PayPalConfigurationInfo.CurrencyCode);
        return "payment";
    }
}
