package de.hybris.questions.daos.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.questions.daos.QuestionDAO;
import de.hybris.questions.model.QuestionModel;

import java.util.Date;
import java.util.List;

public class DefaultQuestionDAO implements QuestionDAO {

    private static final String FIND_QUESTIONS = "SELECT {p:" + QuestionModel.PK + "} FROM {"
            + QuestionModel._TYPECODE + " AS p}";

    private static final String FIND_QUESTIONS_AFTER_TIME = "SELECT {p:" + QuestionModel.PK + "} FROM {"
            + QuestionModel._TYPECODE + " AS p} WHERE {p:" + QuestionModel.CREATIONTIME + "}>=?time ";

    private FlexibleSearchService flexibleSearchService;

    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    @Override
    public List<QuestionModel> findQuestions() {
        return flexibleSearchService.<QuestionModel> search(new FlexibleSearchQuery(FIND_QUESTIONS)).getResult();
    }

    @Override
    public List<QuestionModel> findQuestionsAfterDate(Date date) {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(FIND_QUESTIONS_AFTER_TIME);
        query.addQueryParameter("time", date);
        return flexibleSearchService.<QuestionModel> search(query).getResult();
    }
}
