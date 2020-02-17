package org.walter.app.configuration.jpa.fund;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.walter.base.constant.MultiTenantConstant;
import org.walter.base.service.MultiTenantContextHolder;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Objects;

@Component
public class MultiTenantFundConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {
    @Autowired
    @Qualifier("fundMultiTenantRoutingDataSource")
    private Map<String, DataSource> fundMultiTenantRoutingDataSource;

    @Override
    protected DataSource selectAnyDataSource() {
        return fundMultiTenantRoutingDataSource.get(MultiTenantConstant.DEFAULT_TENANT_ID);
    }

    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
        String currentContextTenantId = MultiTenantContextHolder.getContextTenantId();
        if(tenantIdentifier == null){
            return selectAnyDataSource();
        }else if(Objects.equals(tenantIdentifier, currentContextTenantId)){
            return fundMultiTenantRoutingDataSource.get(tenantIdentifier);
        }else{
            String msg = String.format("tenantId invalid: currentContextTenantId=%s, tenantIdentifier=%s",
                    currentContextTenantId, tenantIdentifier);
            throw new RuntimeException(msg);
        }
    }
}
