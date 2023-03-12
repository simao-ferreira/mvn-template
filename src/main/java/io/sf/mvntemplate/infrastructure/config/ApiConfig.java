package io.sf.mvntemplate.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    @Bean
    public OpenAPI jokeAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Template API")
                        .description("Template API")
                        .version("v0.0.1")
                );
    }
}

