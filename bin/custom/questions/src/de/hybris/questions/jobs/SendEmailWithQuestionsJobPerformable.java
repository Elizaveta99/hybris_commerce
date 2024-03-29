package de.hybris.questions.jobs;

import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.services.BaseStoreService;
import de.hybris.questions.model.QuestionModel;
import de.hybris.questions.model.SendEmailWithQuestionsCronJobModel;
import de.hybris.questions.model.SendQuestionsEmailProcessModel;
import de.hybris.questions.service.QuestionsService;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;

public class SendEmailWithQuestionsJobPerformable extends AbstractJobPerformable<SendEmailWithQuestionsCronJobModel> {

    private static final Logger LOG = Logger.getLogger(SendEmailWithQuestionsJobPerformable.class);

    private ModelService modelService;
    private EmailService emailService;
    private BusinessProcessService businessProcessService;
    private QuestionsService questionsService;
    private BaseSiteService baseSiteService;
    private BaseStoreService baseStoreService;

    @Override
    public PerformResult perform(SendEmailWithQuestionsCronJobModel sendEmailWithQuestionsCronJobModel) {
        List<QuestionModel> questionModels;

        Date time = sendEmailWithQuestionsCronJobModel.getLastStartTime();
        if (time != null) {
            questionModels = questionsService.getQuestionsAfterDate(time);
        } else {
            questionModels = questionsService.getQuestions();
        }


        if (questionModels.isEmpty()) {
            LOG.info("Send Email Job: there are no new questions");
        } else {
            SendQuestionsEmailProcessModel sendQuestionsEmailProcessModel = (SendQuestionsEmailProcessModel) getBusinessProcessService()
                    .createProcess("questions-" + System.currentTimeMillis(), "sendQuestionsEmailProcess");

            sendQuestionsEmailProcessModel.setCustomer((CustomerModel) sendEmailWithQuestionsCronJobModel.getSessionUser());
            sendQuestionsEmailProcessModel.setStore(baseStoreService.getBaseStoreForUid("electronics"));
            sendQuestionsEmailProcessModel.setSite(baseSiteService.getBaseSiteForUID("electronics"));
            sendQuestionsEmailProcessModel.setLanguage(baseSiteService.getBaseSiteForUID("electronics").getDefaultLanguage());

            sendQuestionsEmailProcessModel.setQuestions(questionModels);
            getModelService().save(sendQuestionsEmailProcessModel);
            getBusinessProcessService().startProcess(sendQuestionsEmailProcessModel);
            LOG.info("Send Email Job: email with new questions");
        }

        sendEmailWithQuestionsCronJobModel.setLastStartTime(sendEmailWithQuestionsCronJobModel.getStartTime());
        modelService.save(sendEmailWithQuestionsCronJobModel);
        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    public ModelService getModelService() {
        return modelService;
    }

    @Override
    public void setModelService(ModelService modelService) {
        this.modelService = modelService;
    }

    public EmailService getEmailService() {
        return emailService;
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public BusinessProcessService getBusinessProcessService() {
        return businessProcessService;
    }

    public void setBusinessProcessService(BusinessProcessService businessProcessService) {
        this.businessProcessService = businessProcessService;
    }

    public void setQuestionsService(QuestionsService questionsService) {
        this.questionsService = questionsService;
    }

    public void setBaseSiteService(BaseSiteService baseSiteService) {
        this.baseSiteService = baseSiteService;
    }

    public void setBaseStoreService(BaseStoreService baseStoreService) {
        this.baseStoreService = baseStoreService;
    }
}
