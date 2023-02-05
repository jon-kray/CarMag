package ru.ecosystem.carsale.app.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

import static java.util.Objects.requireNonNull;

@Configuration
@EnableTransactionManagement
public class DataBaseConfiguration {

    @Bean
    @Primary
    public DataSource dataSource(Environment environment) {
        var dataSource = new DriverManagerDataSource();
        dataSource.setUsername(requireNonNull(environment.getProperty("datasource.username")));
        dataSource.setPassword(requireNonNull(environment.getProperty("datasource.password")));
        dataSource.setUrl(requireNonNull(environment.getProperty("datasource.url")));
        dataSource.setDriverClassName(requireNonNull(environment.getProperty("datasource.driver")));
        return dataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean(DataSource dataSource) {
        var sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("ru.ecosystem.carsale.app.model");
        var properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "none");
        properties.setProperty("hibernate.show_sql", "true");
        sessionFactory.setHibernateProperties(properties);
        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
        var transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

}
