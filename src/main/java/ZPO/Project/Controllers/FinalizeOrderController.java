package ZPO.Project.Controllers;

import ZPO.Project.Entities.Zamowienie;
import ZPO.Project.Models.PaymentStatusModel;
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
public class FinalizeOrderController {
    @Autowired
    private PasiekaService pasiekaService;

    @GetMapping(StaticRoutesName.FINALIZE_ORDER + StaticRoutesName.GOOD)
    public String finalizeGood(Model model, @PathVariable Long orderId) {
        Zamowienie zamowienie = pasiekaService.GetZamowienieById(orderId);
        boolean isOk =  PayPalUtilities.CheckFinalizePayment(zamowienie.getId(), zamowienie.getPlatnosc().getPayPalId(), new PayPalConfigurationInfo());
        PaymentStatusModel paymentStatusModel = new PaymentStatusModel();
        paymentStatusModel.IsOk = isOk;
        paymentStatusModel.Status = isOk ? "Oplacono" : "Platnosc PayPal nie zaakceptowana" ;
        paymentStatusModel.OrderId = orderId;
        paymentStatusModel.PayPalId =  zamowienie.getPlatnosc().getPayPalId();
        model.addAttribute("paymentStatusModel",paymentStatusModel);
        return "finalize";
    }


    @GetMapping(StaticRoutesName.FINALIZE_ORDER + StaticRoutesName.BAD)
    public String finalizeBad(Model model, @PathVariable Long orderId) {
        PaymentStatusModel paymentStatusModel = new PaymentStatusModel();
        paymentStatusModel.IsOk = false;
        paymentStatusModel.Status = "Platnosc PayPal nie zaakceptowana" ;
        paymentStatusModel.OrderId = orderId;
        paymentStatusModel.PayPalId =  null;
        paymentStatusModel.ReturnUrl =  StaticRoutesName.GetPaymentURL(orderId);
        model.addAttribute("paymentStatusModel",paymentStatusModel);

        return "finalize";
    }
}
