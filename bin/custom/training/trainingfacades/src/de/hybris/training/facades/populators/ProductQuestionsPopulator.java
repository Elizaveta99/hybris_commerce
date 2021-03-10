package de.hybris.training.facades.populators;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaFormatModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.media.MediaService;
import de.hybris.questions.model.QuestionModel;
import de.hybris.training.facades.constants.TrainingFacadesConstants;
import questions.data.QuestionData;

import java.util.Optional;

public class ProductQuestionsPopulator implements Populator<ProductModel, ProductData> {

    private Converter<QuestionModel, QuestionData> questionsConverter;
    private MediaService mediaService;

    public void setQuestionsConverter(Converter<QuestionModel, QuestionData> questionsConverter) {
        this.questionsConverter = questionsConverter;
    }

    public void setMediaService(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @Override
    public void populate(ProductModel productModel, ProductData productData) throws ConversionException {
        Optional.ofNullable(productModel.getQuestions())
                .ifPresent(questions -> {
                    productData.setQuestions(Converters.convertAll(questions, questionsConverter));
                    productData.setQuestionsCount((long) questions.size());
                    productData.setImageURL(getImageURL(productModel, mediaService.getFormat(TrainingFacadesConstants.FORMAT)));
                });

    }

    protected String getImageURL(ProductModel sm, MediaFormatModel format) {
        MediaContainerModel container = sm.getDiscussedImage();
        if (container != null) {
            return mediaService.getMediaByFormat(container, format).getDownloadURL();
        }
        return null;
    }
}
