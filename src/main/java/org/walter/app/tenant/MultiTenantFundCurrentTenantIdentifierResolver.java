package org.walter.app.tenant;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;
import org.walter.base.service.MultiTenantContextHolder;

@Component
public class MultiTenantFundCurrentTenantIdentifierResolver implements CurrentTenantIdentifierResolver {
    @Override
    public String resolveCurrentTenantIdentifier() {
        return MultiTenantContextHolder.getContextTenantId();
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
