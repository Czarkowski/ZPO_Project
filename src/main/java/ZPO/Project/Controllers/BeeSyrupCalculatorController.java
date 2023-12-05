package ZPO.Project.Controllers;

import ZPO.Project.Enumes.*;
import ZPO.Project.Models.BeeSyrupForm;
import ZPO.Project.MyUtilities.MyStaticUtilities;
import ZPO.Project.Routing.StaticRoutesName;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BeeSyrupCalculatorController {

    @GetMapping(StaticRoutesName.BEE_SYRUP_CALCULATOR)
    public String showCalculatorForm(Model model) {
        model.addAttribute("BaseElementFromSpring", MyStaticUtilities.GetJsonString(EnumSyrupBaseElement.values()));
        model.addAttribute("RatiosFromSpring", MyStaticUtilities.GetJsonString(EnumSugarWaterRatio.values()));
        return "bee_syrup_calculator";
    }
}

