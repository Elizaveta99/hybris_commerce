package de.hybris.questions.daos;

import de.hybris.questions.model.QuestionModel;

import java.util.Date;
import java.util.List;

public interface QuestionDAO {
    List<QuestionModel> findQuestions();
    List<QuestionModel> findQuestionsAfterDate(Date date);
}
