package de.hybris.questions.service.impl;

import de.hybris.questions.daos.QuestionDAO;
import de.hybris.questions.model.QuestionModel;
import de.hybris.questions.service.QuestionsService;

import java.util.Date;
import java.util.List;

public class DefaultQuestionsService implements QuestionsService {

    private QuestionDAO questionDAO;

    @Override
    public List<QuestionModel> getQuestions() {
        return questionDAO.findQuestions();
    }

    @Override
    public List<QuestionModel> getQuestionsAfterDate(Date date) {
        return questionDAO.findQuestionsAfterDate(date);
    }

    public void setQuestionDAO(QuestionDAO questionDAO) {
        this.questionDAO = questionDAO;
    }
}
