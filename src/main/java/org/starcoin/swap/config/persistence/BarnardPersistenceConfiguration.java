package org.starcoin.swap.config.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
        basePackages = "org.starcoin.swap.repository.barnard",
        entityManagerFactoryRef = "barnardEntityManagerFactory",
        transactionManagerRef = "barnardTransactionManager"
)
public class BarnardPersistenceConfiguration {
    @Autowired
    private JpaProperties jpaProperties;
    @Autowired
    @Qualifier("barnardDataSource")
    private DataSource barnardDataSource;

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.barnard")
    public DataSource barnardDataSource() {
        return DataSourceBuilder.create().build();
    }

    private Map<String, String> getVendorProperties() {
        jpaProperties.setDatabase(Database.POSTGRESQL);
        return jpaProperties.getProperties();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean barnardEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(barnardDataSource)
                .packages("org.starcoin.swap.entity") // 设置实体类所在位置
                .persistenceUnit("barnardPersistenceUnit")
                .properties(getVendorProperties())
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager barnardTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(barnardEntityManagerFactory(builder).getObject());
    }
}
