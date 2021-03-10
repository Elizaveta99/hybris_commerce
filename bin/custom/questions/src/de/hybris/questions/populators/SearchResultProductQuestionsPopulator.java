package de.hybris.questions.populators;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import java.util.Optional;

public class SearchResultProductQuestionsPopulator implements Populator<SearchResultValueData, ProductData> {

    @Override
    public void populate(SearchResultValueData searchResultValueData, ProductData productData) throws ConversionException {
        Optional.ofNullable((Long)searchResultValueData.getValues().get("questionsCount"))
                .ifPresent(productData::setQuestionsCount);
    }

}
