package ZPO.Project.Controllers;

import ZPO.Project.Routing.StaticRoutesName;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FinalizeOrderController {


    @GetMapping(StaticRoutesName.FINALIZE_ORDER)
    public String shop(Model model, @PathVariable Long orderId) {
        return "finalize";
    }
}
