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
import org.walter.base.properties.JdbcDefaultProperties;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages={"org.walter.base.repository"},
        entityManagerFactoryRef = "defaultEntityManagerFactory",
        transactionManagerRef = "defaultJpaTransactionManager")
public class JpaDefaultConfig {
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
    public PlatformTransactionManager defaultJpaTransactionManager() {
        return new JpaTransactionManager(defaultEntityManagerFactory().getObject());
    }
}
