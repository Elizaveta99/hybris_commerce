package de.hybris.questions.attributehandlers;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;
import org.apache.commons.collections4.CollectionUtils;

public class AnsweredQuestionsCountDynamicAttributeHandler extends AbstractDynamicAttributeHandler<Integer, ProductModel> {

    @Override
    public Integer get(ProductModel model) {
        return (int) CollectionUtils
                .emptyIfNull(model.getQuestions())
                .stream()
                .filter(questionModel -> !(questionModel.getAnswer().isEmpty())).count();
    }
}
