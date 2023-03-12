package io.sf.mvntemplate.infrastructure.config;

import io.sf.mvntemplate.domain.service.ChuckNorrisConnector;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


@Configuration
public class RetrofitConfig {

    @Resource
    private ConfigurationProvider config;

    @Bean
    ChuckNorrisConnector jokeConnector() {
        return new Retrofit.Builder()
                .baseUrl(config.getUrl())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(ChuckNorrisConnector.class);
    }

}
