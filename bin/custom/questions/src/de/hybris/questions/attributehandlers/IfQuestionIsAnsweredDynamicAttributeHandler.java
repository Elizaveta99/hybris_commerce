package de.hybris.questions.attributehandlers;

import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;
import de.hybris.questions.model.QuestionModel;
import org.apache.commons.lang3.StringUtils;

public class IfQuestionIsAnsweredDynamicAttributeHandler extends AbstractDynamicAttributeHandler<Boolean, QuestionModel> {

    @Override
    public Boolean get(QuestionModel model) {
        return !StringUtils.isBlank(model.getAnswer());
    }
}
