package de.hybris.questions.service;

import de.hybris.questions.model.QuestionModel;

import java.util.Date;
import java.util.List;

public interface QuestionsService {
    List<QuestionModel> getQuestions();
    List<QuestionModel> getQuestionsAfterDate(Date date);
}
