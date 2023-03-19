package io.sf.mvntemplate.domain.service;

import io.sf.mvntemplate.api.model.JokeResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JokeServiceImpl implements JokeService {

    @Resource
    private ChuckNorrisClient chuckNorrisClient;

    @Override
    public JokeResponse getJoke() {

        return JokeResponse.builder()
                .joke(chuckNorrisClient.getChuckNorrisJoke().getValue())
                .build();
    }

    @Override
    public JokeResponse getCategoryJoke(JokeCategory category) {
        return JokeResponse.builder()
                .joke(chuckNorrisClient.getChuckNorrisJokeFromCategory(category).getValue())
                .build();
    }

}
