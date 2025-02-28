package com.example.multi_datasource.config;


import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.multi_datasource.studentRepository",
        entityManagerFactoryRef = "studentEntityManagerFactory",
        transactionManagerRef = "studentTransactionManager"

)
public class StudentDatasourceConfig {
    @Primary
    @Bean(name = "studentProperties")
    @ConfigurationProperties("spring.datasource.student")
    public DataSourceProperties datasourceProperties(){
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "studentDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.student")
    public DataSource datasource(@Qualifier("studentProperties")DataSourceProperties properties){
        System.out.println(properties.toString()+" hi .......");
        return properties.initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean(name ="studentEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("studentDatasource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.example.multi_datasource.student");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties());

        return em;
    }
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        properties.setProperty("hibernate.show_sql","true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        return properties;
    }

//    @Primary
//    @Bean(name ="studentEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder, @Qualifier("studentDatasource")DataSource dataSource){
//        return builder.dataSource(dataSource).packages("com.example.multi-datasource.student").persistenceUnit("students").build();
//    }
    @Primary
    @Bean(name = "studentTransactionManager")
    @ConfigurationProperties("spring.jpa")
    public PlatformTransactionManager
    transactionManager(@Qualifier("studentEntityManagerFactory") EntityManagerFactory entityManagerFactory){

        return new JpaTransactionManager(entityManagerFactory);
    }


}
