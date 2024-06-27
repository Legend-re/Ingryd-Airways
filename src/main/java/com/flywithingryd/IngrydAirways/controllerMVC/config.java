package com.flywithingryd.IngrydAirways.controllerMVC;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.util.MimeType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class config implements WebMvcConfigurer {
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(false)
                .favorPathExtension(true)
                .mediaType("js", MediaType.asMediaType(MimeType.valueOf("application/javascript")));
    }

}
