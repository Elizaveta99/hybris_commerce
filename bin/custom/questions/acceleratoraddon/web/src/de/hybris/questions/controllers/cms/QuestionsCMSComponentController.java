package de.hybris.questions.controllers.cms;

import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import de.hybris.questions.constants.QuestionsWebConstants;
import de.hybris.questions.model.QuestionsCMSComponentModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller("QuestionsCMSComponentController")
@RequestMapping(value = "/view/QuestionsCMSComponentController")
public class QuestionsCMSComponentController extends AbstractCMSAddOnComponentController<QuestionsCMSComponentModel> {

    @Override
    protected void fillModel(HttpServletRequest request, Model model, QuestionsCMSComponentModel component) {
        model.addAttribute(QuestionsWebConstants.NUMBER_OF_QUESTIONS_TO_SHOW, component.getNumberOfQuestionsToShow());
        model.addAttribute(QuestionsWebConstants.FONT_SIZE, component.getFontSize());
    }
}
