package org.walter.base.tenant;

import org.walter.app.constant.MultiTenantDataSourceTypeEnum;
import org.walter.base.configuration.TenantDataSourceMapHolder;
import org.walter.base.constant.MultiTenantConstant;

import javax.sql.DataSource;
import java.util.Map;

public abstract class AbstractMultiTenantConfig {

    protected Map<String, DataSource> initMultiTenantDataSourceMap(MultiTenantDataSourceTypeEnum multiTenantDataSourceType){
        Map<String, DataSource> targetDataSources = TenantDataSourceMapHolder.get(multiTenantDataSourceType.getCode());
        DataSource defaultDataSource = TenantDataSourceMapHolder.get(MultiTenantConstant.DEFAULT_TENANT_ID)
                .get(MultiTenantConstant.DEFAULT_TENANT_ID);
        targetDataSources.put(MultiTenantConstant.DEFAULT_TENANT_ID, defaultDataSource);
        return targetDataSources;
    }
}
