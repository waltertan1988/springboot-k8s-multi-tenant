package org.walter.base.tenant;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.walter.base.constant.MultiTenantConstant;
import org.walter.base.entity.JpaAclTenantDataSource;
import org.walter.base.repository.AclTenantDataSourceRepository;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractMultiTenantConfig {
    @Autowired
    @Qualifier("defaultDataSource")
    protected DataSource defaultDataSource;
    @Autowired
    protected AclTenantDataSourceRepository aclTenantDataSourceRepository;

    protected Map<String, DataSource> initMultiTenantDataSourceMap(){
        Map<String, DataSource> targetDataSources = new ConcurrentHashMap<>();
        targetDataSources.put(MultiTenantConstant.DEFAULT_TENANT_ID, defaultDataSource);
        return targetDataSources;
    }

    protected DataSource createDataSource(JpaAclTenantDataSource jpaAclTenantDataSource){
        HikariConfig jdbcConfig = new HikariConfig();
        String poolName = String.format("HikariPool-%s-%s", jpaAclTenantDataSource.getTenantId(), jpaAclTenantDataSource.getDataSourceId());
        jdbcConfig.setPoolName(poolName);
        jdbcConfig.setDriverClassName(jpaAclTenantDataSource.getDriverClass());
        jdbcConfig.setJdbcUrl(jpaAclTenantDataSource.getJdbcUrl());
        jdbcConfig.setUsername(jpaAclTenantDataSource.getDbUsername());
        jdbcConfig.setPassword(jpaAclTenantDataSource.getDbPassword());
        return new HikariDataSource(jdbcConfig);
    }
}
