package com.soterianetworks.spample.server.config;

import com.soterianetworks.spase.context.SpRequestInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@Configuration
public class SpampleWebMvcConfigurer extends WebMvcConfigurerAdapter {

    /**
     * Enable request interceptor
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(spRequestInterceptor())
                //  exclude spring boot actuator
                .excludePathPatterns("/health",
                                     "/info",
                                     "/error",
                                     "/websocket/**",
                                     "/topic/**",
                                     "/app/**",
                                     "/devops/**")
                //  exclude swagger
                //.excludePathPatterns("/swagger-ui.html", "/swagger-resources", "/configuration/**", "/v2/api-docs")
                .addPathPatterns("/**");
    }

    @Bean
    public SpRequestInterceptor spRequestInterceptor() {
        return new SpRequestInterceptor();
    }

    /**
     * i.e. allow the file name in /rest/idp/abc.xml to be mapped to a parameter,
     * otherwise, it will chop off the .xml portion
     *
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }

    /**
     * Enforce utf-8 encoding for all traffic
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean shallowEtagHeaderFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        registration.setFilter(filter);
        return registration;
    }

    /**
     * Support localization
     */
    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
        resolver.setDefaultLocale(new Locale("en_US"));
        return resolver;
    }

    /**
     * Support localization message
     */
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:locale/messages");
        messageSource.setCacheSeconds(3600); //refresh cache once per hour

        return messageSource;
    }

    @Bean
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
