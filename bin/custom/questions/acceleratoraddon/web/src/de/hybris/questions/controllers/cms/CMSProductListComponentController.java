package de.hybris.questions.controllers.cms;

import de.hybris.platform.addonsupport.controllers.cms.GenericCMSAddOnComponentController;
import de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel;
import de.hybris.questions.constants.QuestionsConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("CMSProductListComponentController")
@RequestMapping(value = "/view/CMSProductListComponentController")
public class CMSProductListComponentController extends GenericCMSAddOnComponentController {

    @Override
    protected String getView(AbstractCMSComponentModel component) {
        return "addon:" + "/" + QuestionsConstants.EXTENSIONNAME + "/" + getCmsComponentFolder() + "/"
                + getViewResourceName(component);
    }

}
