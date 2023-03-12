package io.sf.mvntemplate.domain.service;

import io.sf.mvntemplate.domain.service.exception.ChuckNorrisResponseException;
import io.sf.mvntemplate.domain.service.model.JokeDTO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Slf4j
@Service
public class JokeServiceImpl implements JokeService {

    @Resource
    private ChuckNorrisConnector connector;

    @Override
    public String getJoke() {
        return joke(connector.getJoke());
    }

    private String joke(Call<JokeDTO> call) {
        try {
            Response<JokeDTO> response = call.execute();

            if (!response.isSuccessful() || response.body() == null) {
                log.warn("Call was unsuccessful: {}", response.errorBody());
                throw new ChuckNorrisResponseException(response.raw());
            }

            return response.body().getValue();
        } catch (IOException ex) {
            throw new ChuckNorrisResponseException(ex);
        }
    }
}
