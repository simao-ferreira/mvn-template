package io.sf.mvntemplate.domain.service;

import io.sf.mvntemplate.api.model.JokeResponse;
import io.sf.mvntemplate.domain.service.model.JokeDTO;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
class JokeServiceImplTest {

    @InjectMocks
    private JokeServiceImpl jokeService;

    @Mock
    private ChuckNorrisClient client;

    @Test
    @SneakyThrows
    void whenJokeIsRequested_thenReturnSuccessfulRetrievedJoke() {
        //given
        JokeResponse expectedResponse = JokeResponse.builder()
                .joke("When Chuck Norris throws exceptions, it’s across the room")
                .build();
        when(client.getChuckNorrisJoke()).thenReturn(
                JokeDTO.builder()
                        .value("When Chuck Norris throws exceptions, it’s across the room")
                        .build());
        //when
        JokeResponse response = jokeService.getJoke();
        //then
        assertEquals(expectedResponse, response);
    }

    @Test
    @SneakyThrows
    void whenCategoryJokeIsRequested_thenReturnSuccessfulRetrievedJoke() {
        //given
        JokeResponse expectedResponse = JokeResponse.builder()
                .joke("When Chuck Norris throws exceptions, it’s across the room")
                .build();
        when(client.getChuckNorrisJokeFromCategory(JokeCategory.CAREER)).thenReturn(
                JokeDTO.builder()
                        .value("When Chuck Norris throws exceptions, it’s across the room")
                        .build());
        //when
        JokeResponse response = jokeService.getCategoryJoke(JokeCategory.CAREER);
        //then
        assertEquals(expectedResponse, response);
    }

}
