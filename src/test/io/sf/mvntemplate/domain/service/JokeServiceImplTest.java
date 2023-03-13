package io.sf.mvntemplate.domain.service;

import io.sf.mvntemplate.domain.service.model.JokeDTO;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import retrofit2.Response;
import retrofit2.mock.Calls;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
class JokeServiceImplTest {

    @InjectMocks
    private JokeServiceImpl jokeService;

    @Mock
    private ChuckNorrisConnector connector;

    @Test
    @SneakyThrows
    void whenJokeIsRequested_thenReturnSuccessfulRetrievedJoke() {
        //given
        when(connector.getJoke()).thenReturn(Calls.response(Response.success(JokeDTO.builder()
                .value("When Chuck Norris throws exceptions, it’s across the room")
                .build())));
        //when
        String response = jokeService.getJoke();
        //then
        assertEquals("When Chuck Norris throws exceptions, it’s across the room", response);
    }

}
