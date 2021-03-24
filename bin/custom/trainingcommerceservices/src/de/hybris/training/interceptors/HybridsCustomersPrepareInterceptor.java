package de.hybris.training.interceptors;

import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserGroupModel;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.user.daos.UserGroupDao;
import org.springframework.beans.factory.annotation.Required;

import java.util.HashSet;
import java.util.Set;

public class HybridsCustomersPrepareInterceptor implements PrepareInterceptor {

    private static final String HYBRIDS = "hybrids";
    private UserGroupDao userGroupDao;

    @Override
    public void onPrepare(Object model, InterceptorContext ctx) throws InterceptorException {
        if (model instanceof CustomerModel) {
            CustomerModel customer = (CustomerModel) model;
            UserGroupModel hybrids = userGroupDao.findUserGroupByUid(HYBRIDS);

            if (Boolean.TRUE.equals(customer.getIsInternal())) {
                if (!customer.getGroups().contains(hybrids)) {
                    Set<PrincipalGroupModel> newGroups = new HashSet<PrincipalGroupModel>(customer.getGroups());
                    newGroups.add(hybrids);
                    customer.setGroups(newGroups);
                }
            }
            else {
                if (customer.getGroups().contains(hybrids)) {
                    Set<PrincipalGroupModel> newGroups = new HashSet<PrincipalGroupModel>(customer.getGroups());
                    newGroups.remove(hybrids);
                    customer.setGroups(newGroups);
                }
            }
        }
    }

    @Required
    public void setUserGroupDao(UserGroupDao userGroupDao) {
        this.userGroupDao = userGroupDao;
    }
}
