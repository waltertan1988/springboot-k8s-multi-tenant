package org.walter.base.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.walter.base.constant.MultiTenantConstant;
import org.walter.base.entity.JpaAclTenantDataSource;
import org.walter.base.properties.JdbcDefaultProperties;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages={"org.walter.base.repository"},
        entityManagerFactoryRef = "defaultEntityManagerFactory",
        transactionManagerRef = "defaultJpaTransactionManager")
public class JpaDefaultConfig implements InitializingBean {
    @Autowired
    private JdbcDefaultProperties jdbcDefaultProperties;

    /**
     * 系统默认数据源
     * @return
     */
    @Bean
    public DataSource defaultDataSource(){
        HikariConfig jdbcConfig = new HikariConfig();
        jdbcConfig.setPoolName("HikariPool-default");
        jdbcConfig.setDriverClassName(jdbcDefaultProperties.getDriverClass());
        jdbcConfig.setJdbcUrl(jdbcDefaultProperties.getUrl());
        jdbcConfig.setUsername(jdbcDefaultProperties.getUsername());
        jdbcConfig.setPassword(jdbcDefaultProperties.getPassword());
        return new HikariDataSource(jdbcConfig);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean defaultEntityManagerFactory(){
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(defaultDataSource());
        //加载实体类
        bean.setPackagesToScan("org.walter.base.entity");
        bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        bean.setJpaProperties(properties);
        return bean;
    }

    @Bean
    @Primary
    public PlatformTransactionManager defaultJpaTransactionManager() {
        return new JpaTransactionManager(defaultEntityManagerFactory().getObject());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initTenantDataSourceMapHolder();
    }

    private void initTenantDataSourceMapHolder() {
        DataSource defaultDataSource = defaultDataSource();
        TenantDataSourceMapHolder.put(MultiTenantConstant.DEFAULT_TENANT_ID,
                MultiTenantConstant.DEFAULT_TENANT_ID,defaultDataSource);

        final String SELECT_TENANT_DS_SQL = String.format("select * from %s", jdbcDefaultProperties.getTenantDataSourceTable());
        new JdbcTemplate(defaultDataSource).queryForList(SELECT_TENANT_DS_SQL).forEach(row -> {
            JpaAclTenantDataSource jpaAclTenantDataSource = new JpaAclTenantDataSource();
            jpaAclTenantDataSource.setTenantId(row.get("tenant_id").toString());
            jpaAclTenantDataSource.setDataSourceId(row.get("datasource_id").toString());
            jpaAclTenantDataSource.setDriverClass(row.get("driver_class").toString());
            jpaAclTenantDataSource.setJdbcUrl(row.get("jdbc_url").toString());
            jpaAclTenantDataSource.setDbUsername(row.get("db_username").toString());
            jpaAclTenantDataSource.setDbPassword(row.get("db_password").toString());
            DataSource dataSource = createDataSource(jpaAclTenantDataSource);
            TenantDataSourceMapHolder.put(jpaAclTenantDataSource.getDataSourceId(),
                    jpaAclTenantDataSource.getTenantId(), dataSource);
        });
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
