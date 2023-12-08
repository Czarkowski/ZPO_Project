package ZPO.Project.RestApiController;

import ZPO.Project.ApiModels.CalculationRequest;
import ZPO.Project.ApiModels.CalculationResponse;
import ZPO.Project.Entities.Zamowienie;
import ZPO.Project.Models.Product;
import ZPO.Project.MyUtilities.MyStaticUtilities;
import ZPO.Project.MyUtilities.UrlHelper;
import ZPO.Project.PayPal.*;
import ZPO.Project.Routing.APIRoutesName;
import ZPO.Project.Routing.PayPalApiRoutes;
import ZPO.Project.Services.PasiekaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PayPalApiRoutes.PREFIX)
public class PayPalApiController {
    @Autowired
    private PasiekaService pasiekaService;
    @Autowired
    private PayPalUtilities payPalUtilities;


    @PostMapping(PayPalApiRoutes.CREATE_ORDER + PayPalApiRoutes.PP_ZamId)
    public ResponseEntity<PayPalJsResponseJson> createOrder(@PathVariable Long ZamId) {
        var config = new PayPalConfigurationInfo();
        Zamowienie orders = pasiekaService.GetZamowieniebyId(ZamId);
        
        String returnUrl = UrlHelper.GetUrlOrderFinalize(ZamId, true);
        String cancelUrl = UrlHelper.GetUrlOrderFinalize(ZamId, false);
        PayPalJsResponseJson payPalJsResponseJson = new PayPalJsResponseJson();
        try
        {
            String payPalOrderDetailsJson = payPalUtilities.CreateOrder(orders, config, returnUrl, cancelUrl);
            PayPalOrderDetailsJson payPalCreateOrderResponse = payPalUtilities.GetObiectFromJsonString(payPalOrderDetailsJson, PayPalOrderDetailsJson.class);
            payPalUtilities.SaveNewPayPalPayment(payPalCreateOrderResponse, ZamId);
            payPalJsResponseJson.orderId = payPalCreateOrderResponse.id;
            payPalJsResponseJson.statusOk = true;
        }
        catch (PayPalException payPalException)
        {
            payPalJsResponseJson.redirectUrl = cancelUrl;
            payPalJsResponseJson.statusOk = false;
            payPalJsResponseJson.exceptionName = payPalException.PayPalExceptionJson.name;
            payPalJsResponseJson.payPalExceptionJson = payPalException.PayPalExceptionJson;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            payPalJsResponseJson.redirectUrl = cancelUrl;
            payPalJsResponseJson.statusOk = false;
        }
        return ResponseEntity.ok(payPalJsResponseJson);
    }

    @PostMapping(PayPalApiRoutes.APPROVE_ORDER + PayPalApiRoutes.PP_ZamId + PayPalApiRoutes.SEPARATOR + PayPalApiRoutes.PP_PayPalId )
    public ResponseEntity<PayPalJsResponseJson> approveOrder(@PathVariable Long ZamId, @PathVariable String PayPalId) {
        var config = new PayPalConfigurationInfo();

        PayPalJsResponseJson payPalJsResponseJson = new PayPalJsResponseJson();
        String returnUrl = UrlHelper.GetUrlOrderFinalize(ZamId, true);
        String cancelUrl = UrlHelper.GetUrlOrderFinalize(ZamId, false);
        try
        {
            var payPalApproveOrderResponse = payPalUtilities.ApproveOrder(PayPalId, config);
            var payPalOrderDetails = payPalUtilities.GetObiectFromJsonString(payPalApproveOrderResponse, PayPalOrderDetailsJson.class);
            payPalUtilities.UpdatePayPalPayment(payPalOrderDetails);
            payPalJsResponseJson.redirectUrl = returnUrl;
            payPalJsResponseJson.orderId = payPalOrderDetails.id;
            payPalJsResponseJson.statusOk = true;
        }
        catch (PayPalException payPalException)
        {
            payPalJsResponseJson.redirectUrl = cancelUrl;
            payPalJsResponseJson.statusOk = false;
            payPalJsResponseJson.exceptionName = payPalException.PayPalExceptionJson.name;
            payPalJsResponseJson.payPalExceptionJson = payPalException.PayPalExceptionJson;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            payPalJsResponseJson.redirectUrl = cancelUrl;
            payPalJsResponseJson.statusOk = false;
        }

        return ResponseEntity.ok(payPalJsResponseJson);
    }

}
