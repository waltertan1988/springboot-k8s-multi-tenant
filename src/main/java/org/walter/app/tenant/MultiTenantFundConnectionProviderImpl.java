package org.walter.app.tenant;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.walter.base.service.MultiTenantContextHolder;
import org.walter.base.tenant.MultiTenantRoutingDataSource;

import javax.sql.DataSource;
import java.util.Objects;

@Component
public class MultiTenantFundConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {
    @Autowired
    @Qualifier("fundMultiTenantRoutingDataSource")
    private MultiTenantRoutingDataSource fundMultiTenantRoutingDataSource;

    @Override
    protected DataSource selectAnyDataSource() {
        return fundMultiTenantRoutingDataSource.getDefaultDataSource();
    }

    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
        String currentContextTenantId = MultiTenantContextHolder.getContextTenantId();
        if(tenantIdentifier == null){
            return selectAnyDataSource();
        }else if(Objects.equals(tenantIdentifier, currentContextTenantId)){
            return fundMultiTenantRoutingDataSource.getDetermineTargetDataSource();
        }else{
            String msg = String.format("tenantId invalid: currentContextTenantId=%s, tenantIdentifier=%s"
                    , currentContextTenantId, tenantIdentifier);
            throw new RuntimeException(msg);
        }
    }
}
