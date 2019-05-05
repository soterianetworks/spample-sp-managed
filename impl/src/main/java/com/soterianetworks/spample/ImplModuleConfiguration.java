package com.soterianetworks.spample;

import com.soterianetworks.spample.domain.enums.Enums;
import com.soterianetworks.spase.domain.enums.i18n.I18nEnumRegistry;
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
        "com.soterianetworks.spample.module.*.**.repository"})
@EnableJpaAuditing()
@EnableTransactionManagement
public class ImplModuleConfiguration {

    @Bean
    public I18nEnumRegistry I18nEnumRegistry() {
        I18nEnumRegistry registry = new I18nEnumRegistry();
        registry.setScanPackage(Enums.class.getPackage().getName());
        return registry;
    }

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

}
