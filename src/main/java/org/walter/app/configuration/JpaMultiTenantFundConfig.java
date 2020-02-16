package org.walter.app.configuration;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.walter.app.constant.MultiTenantDataSourceTypeEnum;
import org.walter.app.tenant.MultiTenantFundConnectionProviderImpl;
import org.walter.app.tenant.MultiTenantFundCurrentTenantIdentifierResolver;
import org.walter.base.entity.JpaAclTenantDataSource;
import org.walter.base.tenant.AbstractMultiTenantConfig;
import org.walter.base.tenant.MultiTenantRoutingDataSource;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Fund(清结算)的多租户db配置
 */
@Configuration
@EnableJpaRepositories(
        basePackages={"org.walter.app.repository.fund"},
        entityManagerFactoryRef = "fundMultiTenantEntityManagerFactory",
        transactionManagerRef = "fundMultiTenantJpaTransactionManager")
public class JpaMultiTenantFundConfig extends AbstractMultiTenantConfig {
    @Autowired
    private MultiTenantFundConnectionProviderImpl multiTenantFundConnectionProvider;
    @Autowired
    private MultiTenantFundCurrentTenantIdentifierResolver multiTenantFundCurrentTenantIdentifierResolver;

    @Bean
    public MultiTenantRoutingDataSource fundMultiTenantRoutingDataSource(){
        Map<Object, Object> targetDataSources = new ConcurrentHashMap<>();
        List<JpaAclTenantDataSource> jpaAclTenantDataSourceList = aclTenantDataSourceRepository
                .findAllByDataSourceId(MultiTenantDataSourceTypeEnum.FUND.getCode());
        for (JpaAclTenantDataSource tenantDataSource : jpaAclTenantDataSourceList) {
            targetDataSources.put(tenantDataSource.getTenantId(), createDataSource(tenantDataSource));
        }
        return new MultiTenantRoutingDataSource(defaultDataSource, targetDataSources);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean fundMultiTenantEntityManagerFactory(){
        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        properties.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
        properties.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantFundConnectionProvider);
        properties.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, multiTenantFundCurrentTenantIdentifierResolver);

        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
//        bean.setDataSource(fundMultiTenantRoutingDataSource());
        //加载实体类
        bean.setPackagesToScan("org.walter.app.entity.fund");
        bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        bean.setJpaProperties(properties);
        return bean;
    }

    @Bean
    public PlatformTransactionManager fundMultiTenantJpaTransactionManager() {
        return new JpaTransactionManager(fundMultiTenantEntityManagerFactory().getObject());
    }
}
