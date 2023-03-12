package io.sf.mvntemplate.domain.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class JokeServiceImplTest {

    @InjectMocks
    private JokeServiceImpl jokeService;

    @Test
    @SneakyThrows
    void whenJokeIsRequested_thenReturnSuccessfulRetrievedJoke() {
        //TODO
    }

}
