package de.hybris.questions.context;

import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;
import de.hybris.platform.converters.Converters;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.questions.model.QuestionModel;
import de.hybris.questions.model.SendQuestionsEmailProcessModel;
import de.hybris.training.facades.process.email.context.CustomerEmailContext;
import questions.data.QuestionData;

import java.util.List;

public class QuestionsEmailContext extends CustomerEmailContext {

    private List<QuestionData> questions;
    private Converter<QuestionModel, QuestionData> questionsConverter;

    @Override
    public void init(final StoreFrontCustomerProcessModel processModel, final EmailPageModel emailPageModel)
    {
        super.init(processModel, emailPageModel);
        put(FROM_EMAIL, emailPageModel.getFromEmail());
        if (processModel instanceof SendQuestionsEmailProcessModel)
        {
            setQuestions(Converters.convertAll(((SendQuestionsEmailProcessModel) processModel).getQuestions(), questionsConverter));
        }
    }

    public List<QuestionData> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionData> questions) {
        this.questions = questions;
    }

    public void setQuestionsConverter(Converter<QuestionModel, QuestionData> questionsConverter) {
        this.questionsConverter = questionsConverter;
    }
}
