package org.walter.base.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.walter.base.entity.JpaAclTenantDataSource;
import org.walter.base.repository.AclTenantDataSourceRepository;
import org.walter.base.tenant.MultiTenantRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@EnableJpaRepositories(
        basePackages={"org.walter.app.repository"},
        entityManagerFactoryRef = "multiTenantEntityManagerFactory",
        transactionManagerRef = "multiTenantJpaTransactionManager")
public class JpaMultiTenantConfig {
    @Autowired
    private AclTenantDataSourceRepository aclTenantDataSourceRepository;

    /**
     * 多租户动态数据源
     * @return
     */
    @Bean
    public MultiTenantRoutingDataSource multiTenantRoutingDataSource(){
        Map<Object, Object> targetDataSources = new ConcurrentHashMap<>();
        for (JpaAclTenantDataSource tenantDataSource : aclTenantDataSourceRepository.findAll()) {
            targetDataSources.put(tenantDataSource.getTenantId(), createDataSource(tenantDataSource));
        }
        return new MultiTenantRoutingDataSource(null, targetDataSources);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean multiTenantEntityManagerFactory(){
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(multiTenantRoutingDataSource());
        //加载实体类
        bean.setPackagesToScan("org.walter.app.entity");
        bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        bean.setJpaProperties(properties);
        return bean;
    }

    @Bean
    public PlatformTransactionManager multiTenantJpaTransactionManager() {
        return new JpaTransactionManager(multiTenantEntityManagerFactory().getObject());
    }

    private DataSource createDataSource(JpaAclTenantDataSource jpaAclTenantDataSource){
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
