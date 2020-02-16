package org.walter.base.tenant;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.walter.base.entity.JpaAclTenantDataSource;
import org.walter.base.repository.AclTenantDataSourceRepository;

import javax.sql.DataSource;

public abstract class AbstractMultiTenantConfig {
    @Autowired
    @Qualifier("defaultDataSource")
    protected DataSource defaultDataSource;
    @Autowired
    protected AclTenantDataSourceRepository aclTenantDataSourceRepository;

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
