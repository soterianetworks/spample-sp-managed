package com.soterianetworks.spample.test;

import com.soterianetworks.spample.rest.RestModuleConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({RestModuleConfiguration.class})
public class TestWmsApplication extends SpringBootServletInitializer {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TestWmsApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(new Object[]{TestWmsApplication.class}, args);
    }

}
