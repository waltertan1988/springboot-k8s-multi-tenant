package org.walter.base.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@PropertySource("classpath:application-jdbc.yml")
@ConfigurationProperties("default")
public class JdbcDefaultProperties {
    @Value("${driverClass}")
    private String driverClass;
    @Value("${url}")
    private String url;
    @Value("${dbUsername}")
    private String username;
    @Value("${dbPassword}")
    private String password;
}
