package io.sf.mvntemplate.infrastructure.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ConfigurationProvider {

    @Value("${chucknorris.url}")
    private String url;

}
