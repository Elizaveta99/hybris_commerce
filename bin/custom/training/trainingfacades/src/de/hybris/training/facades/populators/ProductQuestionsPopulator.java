package de.hybris.training.facades.populators;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.questions.model.QuestionModel;
import questions.data.QuestionData;

public class ProductQuestionsPopulator implements Populator<ProductModel, ProductData> {

    private Converter<QuestionModel, QuestionData> questionsConverter;

    public void setQuestionsConverter(Converter<QuestionModel, QuestionData> questionsConverter) {
        this.questionsConverter = questionsConverter;
    }

    @Override
    public void populate(ProductModel productModel, ProductData productData) throws ConversionException {
        productData.setQuestions(Converters.convertAll(productModel.getQuestions(), questionsConverter));
    }
}
