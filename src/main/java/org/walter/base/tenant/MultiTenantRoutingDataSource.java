package org.walter.base.tenant;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.walter.base.service.MultiTenantContextHolder;

import javax.sql.DataSource;
import java.util.Map;

public class MultiTenantRoutingDataSource extends AbstractRoutingDataSource {

    /**
     * 多租户动态数据源
     * @param defaultDataSource 默认数据源
     * @param targetDataSources 多租户数据源
     */
    public MultiTenantRoutingDataSource(DataSource defaultDataSource, Map<Object, Object> targetDataSources){
        super.setDefaultTargetDataSource(defaultDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return MultiTenantContextHolder.getContextTenantId();
    }

    public DataSource getDefaultDataSource(){
        MultiTenantContextHolder.switchToDefaultTenant();
        DataSource defaultDataSource = super.determineTargetDataSource();
        MultiTenantContextHolder.switchToUserTenant();
        return defaultDataSource;
    }

    public DataSource getDetermineTargetDataSource(){
        return super.determineTargetDataSource();
    }
}
