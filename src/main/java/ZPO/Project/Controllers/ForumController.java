package ZPO.Project.Controllers;

import ZPO.Project.Models.QuestionModel;
import ZPO.Project.Routing.StaticRoutesName;
import ZPO.Project.Services.PytanieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForumController {

    @Autowired
    PytanieService pytanieService;

    @GetMapping(StaticRoutesName.FORUM)
    public String home(Model model) {
        var pytania = pytanieService.getAllPytania();
        var questions = QuestionModel.GetQuestionsFromPytania(pytania);
        model.addAttribute("QuestionsFromSpring",
                questions);

        return "forum";
    }
}