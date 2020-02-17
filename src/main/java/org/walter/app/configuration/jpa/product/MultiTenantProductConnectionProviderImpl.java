package org.walter.app.configuration.jpa.product;

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
public class MultiTenantProductConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {
    @Autowired
    @Qualifier("productMultiTenantRoutingDataSource")
    private Map<String, DataSource> productMultiTenantRoutingDataSource;

    @Override
    protected DataSource selectAnyDataSource() {
        return productMultiTenantRoutingDataSource.get(MultiTenantConstant.DEFAULT_TENANT_ID);
    }

    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
        String currentContextTenantId = MultiTenantContextHolder.getContextTenantId();
        if(tenantIdentifier == null){
            return selectAnyDataSource();
        }else if(Objects.equals(tenantIdentifier, currentContextTenantId)){
            return productMultiTenantRoutingDataSource.get(tenantIdentifier);
        }else{
            String msg = String.format("tenantId invalid: currentContextTenantId=%s, tenantIdentifier=%s",
                    currentContextTenantId, tenantIdentifier);
            throw new RuntimeException(msg);
        }
    }
}
