package org.walter.app.tenant;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;
import org.walter.base.constant.MultiTenantConstant;
import org.walter.base.service.MultiTenantContextHolder;

@Component
public class MultiTenantFundCurrentTenantIdentifierResolver implements CurrentTenantIdentifierResolver {
    @Override
    public String resolveCurrentTenantIdentifier() {
        String currentContextTenantId = MultiTenantContextHolder.getContextTenantId();
        return currentContextTenantId == null ? MultiTenantConstant.DEFAULT_TENANT_ID : currentContextTenantId;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
