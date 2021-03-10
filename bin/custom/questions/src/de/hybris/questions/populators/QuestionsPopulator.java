package de.hybris.questions.populators;

import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.questions.model.QuestionModel;
import questions.data.QuestionData;

import java.util.Optional;

public class QuestionsPopulator implements Populator<QuestionModel, QuestionData> {

    private Converter<CustomerModel, CustomerData> customerConverter;

    public void setCustomerConverter(Converter<CustomerModel, CustomerData> customerConverter) {
        this.customerConverter = customerConverter;
    }

    @Override
    public void populate(QuestionModel questionModel, QuestionData questionData) throws ConversionException {
        questionData.setCode(questionData.getCode());
        questionData.setQuestion(questionModel.getQuestion());
        questionData.setQuestionCustomer(customerConverter.convert(questionModel.getQuestionCustomer()));
        Optional.ofNullable(questionModel.getAnswer())
                .ifPresent(questionData::setAnswer);
        Optional.ofNullable(questionModel.getAnswerCustomer())
                .ifPresent(customerModel -> questionData.setAnswerCustomer(customerConverter.convert(customerModel)));

    }
}
