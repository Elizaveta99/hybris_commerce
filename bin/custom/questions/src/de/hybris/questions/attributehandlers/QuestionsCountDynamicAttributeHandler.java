package de.hybris.questions.attributehandlers;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Optional;

public class QuestionsCountDynamicAttributeHandler extends AbstractDynamicAttributeHandler<Integer, ProductModel> {

    @Override
    public Integer get(ProductModel model) {
        return CollectionUtils
                .emptyIfNull(model.getQuestions())
                .size();
    }
}
