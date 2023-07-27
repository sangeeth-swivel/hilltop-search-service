package com.hilltop.search.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class ResourceBundleMessageSourceBean {

    @Bean
    public ResourceBundleMessageSource messageSource() {
        var rs = new ResourceBundleMessageSource();
        rs.setBasenames("success", "error");
        rs.setDefaultEncoding("UTF-8");
        rs.setUseCodeAsDefaultMessage(true);
        return rs;
    }
}
