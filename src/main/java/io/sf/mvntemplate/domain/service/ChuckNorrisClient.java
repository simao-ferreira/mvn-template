package io.sf.mvntemplate.domain.service;

import io.sf.mvntemplate.domain.service.model.JokeDTO;
import io.sf.mvntemplate.infrastructure.exception.ChuckNorrisResponseException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Slf4j
@Component
public class ChuckNorrisClient {

    @Resource
    private ChuckNorrisConnector connector;

    public JokeDTO getChuckNorrisJoke() {
        return callJokeApi(connector.getJoke());
    }

    public JokeDTO getChuckNorrisJokeFromCategory(JokeCategory category) {
        return callJokeApi(connector.getJoke(category.name().toLowerCase()));
    }

    private JokeDTO callJokeApi(Call<JokeDTO> call) {
        try {
            Response<JokeDTO> response = call.execute();

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
