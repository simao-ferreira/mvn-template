package io.sf.mvntemplate.domain.service;

import io.sf.mvntemplate.infrastructure.exception.ChuckNorrisResponseException;
import io.sf.mvntemplate.domain.service.model.JokeDTO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import retrofit2.Response;

import java.io.IOException;

@Slf4j
@Component
public class ChuckNorrisClient {

    @Resource
    private ChuckNorrisConnector connector;

    public JokeDTO getChuckNorrisJoke() {
        try {
            Response<JokeDTO> response = connector.getJoke().execute();

            if (!response.isSuccessful() || response.body() == null) {
                log.warn("Call was unsuccessful: {}", response.errorBody());
                throw new ChuckNorrisResponseException(response.raw());
            }

            return response.body();
        } catch (IOException ex) {
            throw new ChuckNorrisResponseException(ex);
        }
    }
}
