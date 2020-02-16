package org.walter.base.tenant;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.walter.base.constant.MultiTenantConstant;

import javax.sql.DataSource;
import java.util.Map;

public class MultiTenantRoutingDataSource extends AbstractRoutingDataSource {

    private DataSource defaultDataSource;

    /**
     * 多租户动态数据源
     * @param defaultDataSource 默认数据源
     * @param targetDataSources 多租户数据源
     */
    public MultiTenantRoutingDataSource(DataSource defaultDataSource, Map<Object, Object> targetDataSources){
        if(targetDataSources.containsKey(MultiTenantConstant.DEFAULT_TENANT_ID)){
            throw new RuntimeException("租户ID不能使用系统值:" + MultiTenantConstant.DEFAULT_TENANT_ID);
        }
        this.defaultDataSource = defaultDataSource;
        // 把默认数据源加入到多租户数据源中
        targetDataSources.put(MultiTenantConstant.DEFAULT_TENANT_ID, defaultDataSource);
        super.setDefaultTargetDataSource(defaultDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return MultiTenantContextHolder.getContextTenantId();
    }

    public DataSource getDefaultDataSource(){
        return this.defaultDataSource;
    }

    public DataSource getDetermineTargetDataSource(){
        return super.determineTargetDataSource();
    }
}
