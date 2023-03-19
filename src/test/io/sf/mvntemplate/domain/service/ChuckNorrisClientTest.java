package io.sf.mvntemplate.domain.service;

import io.sf.mvntemplate.domain.service.model.JokeDTO;
import io.sf.mvntemplate.infrastructure.exception.ChuckNorrisResponseException;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import retrofit2.Response;
import retrofit2.mock.Calls;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class ChuckNorrisClientTest {

    @InjectMocks
    private ChuckNorrisClient client;

    @Mock
    private ChuckNorrisConnector connector;

    @Test
    @SneakyThrows
    void whenJokeIsRequested_shouldReturnSuccessfulResponse() {
        //given
        JokeDTO joke = JokeDTO.builder()
                .value("Chuck Norris can delete the Recycling Bin")
                .build();
        Response<JokeDTO> successfulJoke = Response.success(joke);
        when(connector.getJoke()).thenReturn(Calls.response(successfulJoke));
        //when
        JokeDTO jokeResponse = client.getChuckNorrisJoke();
        //then
        assertNotNull(jokeResponse);
        assertEquals(joke.getValue(), jokeResponse.getValue());
    }

    @Test
    @SneakyThrows
    void whenCategoryJokeIsRequested_shouldReturnSuccessfulResponse() {
        //given
        JokeDTO joke = JokeDTO.builder()
                .value("Chuck Norris can delete the Recycling Bin")
                .build();
        Response<JokeDTO> successfulJoke = Response.success(joke);
        when(connector.getJoke(JokeCategory.MOVIE.name().toLowerCase())).thenReturn(Calls.response(successfulJoke));
        //when
        JokeDTO jokeResponse = client.getChuckNorrisJokeFromCategory(JokeCategory.MOVIE);
        //then
        assertNotNull(jokeResponse);
        assertEquals(joke.getValue(), jokeResponse.getValue());
    }

    @Test
    @SneakyThrows
    void whenJokeIsRequested_andResponseBodyIsNull_shouldThrowChuckNorrisException() {
        //given
        Response<JokeDTO> nullJoke = Response.success(null);
        when(connector.getJoke()).thenReturn(Calls.response(nullJoke));
        //when
        Exception exception = assertThrows(
                ChuckNorrisResponseException.class,
                () -> client.getChuckNorrisJoke()
        );
        //then
        assertEquals(
                "Failed to get joke with Response{protocol=http/1.1, code=200, message=OK, url=http://localhost/}",
                exception.getMessage()
        );
    }

    @Test
    @SneakyThrows
    void whenJokeIsRequested_andResponseIsNotSuccessful_shouldThrowChuckNorrisException() {
        //given
        ResponseBody errorResponse = ResponseBody.create("", null);
        Response<JokeDTO> successfulJoke = Response.error(404, errorResponse);
        when(connector.getJoke()).thenReturn(Calls.response(successfulJoke));
        //when
        Exception exception = assertThrows(
                ChuckNorrisResponseException.class,
                () -> client.getChuckNorrisJoke()
        );
        //then
        assertEquals(
                "Failed to get joke with Response{protocol=http/1.1, code=404, message=Response.error(), url=http://localhost/}",
                exception.getMessage()
        );
    }

}
