package org.walter.app.configuration.jpa;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;
import org.walter.base.constant.MultiTenantConstant;
import org.walter.base.service.MultiTenantContextHolder;

@Component
public class MultiTenantCurrentTenantIdentifierResolver implements CurrentTenantIdentifierResolver {
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
