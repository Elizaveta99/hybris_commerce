package de.hybris.questions.daos.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.questions.daos.TimeOfSendEmailWithQuestionsDAO;
import de.hybris.questions.exception.ItemNotFoundException;
import de.hybris.questions.model.TimeOfSendEmailWithQuestionsModel;

public class DefaultTimeOfSendEmailWithQuestionsDAO implements TimeOfSendEmailWithQuestionsDAO {

    private static final String FIND_TIME = "SELECT {p:" + TimeOfSendEmailWithQuestionsModel.PK + "} FROM {"
            + TimeOfSendEmailWithQuestionsModel._TYPECODE + " AS p} WHERE {p:" + TimeOfSendEmailWithQuestionsModel.CODE + "}=?code ";

    private FlexibleSearchService flexibleSearchService;

    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    @Override
    public TimeOfSendEmailWithQuestionsModel findTime() {
        FlexibleSearchQuery query = new FlexibleSearchQuery(FIND_TIME);
        query.addQueryParameter("code", "timeCode");
        TimeOfSendEmailWithQuestionsModel result;
        try {
            result = flexibleSearchService.<TimeOfSendEmailWithQuestionsModel> search(query).getResult().get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new ItemNotFoundException();
        }
        return result;
    }
}
