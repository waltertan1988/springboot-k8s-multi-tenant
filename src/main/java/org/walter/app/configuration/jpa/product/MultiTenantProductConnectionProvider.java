package org.walter.app.configuration.jpa.product;

import lombok.AllArgsConstructor;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.walter.base.constant.MultiTenantConstant;
import org.walter.base.service.MultiTenantContextHolder;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
public class MultiTenantProductConnectionProvider extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

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
