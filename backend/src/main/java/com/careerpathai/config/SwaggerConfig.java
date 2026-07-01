package com.careerpathai.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI careerPathOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("CareerPathAI API")
                        .description("Career Recommendation System APIs")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Tarun Sri Ram")
                                .email("tarun@example.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Documentation"));
    }
}