package com.flywithingryd.IngrydAirways.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class SwaggerConfig implements WebMvcConfigurer {
    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("users")
                .pathsToMatch("/api/users/**")
                .build();
    }

    @Bean
    public GroupedOpenApi flightApi() {
        return GroupedOpenApi.builder()
                .group("flights")
                .pathsToMatch("/api/flights/**")
                .build();
    }
}
