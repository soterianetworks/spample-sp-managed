package com.soterianetworks.spample.doc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, HibernateJpaAutoConfiguration.class, DataSourceAutoConfiguration.class})
@Import({Swagger2Configuration.class})
@ComponentScan({"com.soterianetworks.spample.rest.context.controller", "com.soterianetworks.spample.rest.mock"})

public class ApidocApplication extends SpringBootServletInitializer {

    /**
     * Support localization
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new AcceptHeaderLocaleResolver();
    }

    /**
     * Support localization message
     */
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:locale/messages");
        messageSource.setCacheSeconds(3600);
        return messageSource;
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApidocApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(new Object[]{ApidocApplication.class}, args);
    }


}