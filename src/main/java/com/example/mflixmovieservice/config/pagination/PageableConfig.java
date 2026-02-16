package com.example.mflixmovieservice.config.pagination;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@Configuration
public class PageableConfig {

    @Bean
    public PageableHandlerMethodArgumentResolverCustomizer customizer() {
        return resolver -> {
            resolver.setMaxPageSize(25);
        };
    }
}
