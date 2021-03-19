package de.hybris.questions.service.impl;

import de.hybris.questions.daos.TimeOfSendEmailWithQuestionsDAO;
import de.hybris.questions.model.TimeOfSendEmailWithQuestionsModel;
import de.hybris.questions.service.TimeOfSendEmailWithQuestionsService;

public class DefaultTimeOfSendEmailWithQuestionsService implements TimeOfSendEmailWithQuestionsService {

    private TimeOfSendEmailWithQuestionsDAO timeOfSendEmailWithQuestionsDAO;

    @Override
    public TimeOfSendEmailWithQuestionsModel getTime() {
        return timeOfSendEmailWithQuestionsDAO.findTime();
    }

    public void setTimeOfSendEmailWithQuestionsDAO(TimeOfSendEmailWithQuestionsDAO timeOfSendEmailWithQuestionsDAO) {
        this.timeOfSendEmailWithQuestionsDAO = timeOfSendEmailWithQuestionsDAO;
    }
}
