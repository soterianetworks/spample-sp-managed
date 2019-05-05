package com.soterianetworks.spample.test.config;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan(basePackages = {"com.soterianetworks.spample.domain.model"})
@ComponentScan({"com.soterianetworks.spample.module"})
@EnableJpaRepositories({"com.soterianetworks.spample.module.*.repository",
        "com.soterianetworks.spample.module.*.repository",
        "com.soterianetworks.spample.module.*.**.repository"})
@EnableJpaAuditing()
@EnableTransactionManagement
public class TestJpaConfigurer {

    /**
     * Bean post-processor for JPA annotations
     *
     * @return
     */
    @Bean
    public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }

    /**
     * This will ensure that hibernate or jpa exceptions are automatically translated into
     * Spring's generic DataAccessException hierarchy for those classes annotated with Repository
     *
     * @return
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public SessionFactory sessionFactory(HibernateEntityManagerFactory hibernateEntityManagerFactory) {
        return hibernateEntityManagerFactory.getSessionFactory();
    }
}
