package io.sf.mvntemplate.api.controller;

import io.sf.mvntemplate.infrastructure.exception.ChuckNorrisResponseException;
import io.sf.mvntemplate.api.model.JokeResponse;
import io.sf.mvntemplate.domain.service.JokeService;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import retrofit2.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class JokeControllerTest {

    @Resource
    private MockMvc mockMvc;

    @MockBean
    private JokeService jokeService;

    @Test
    @SneakyThrows
    void whenJokeEndpointIsTriggered_shouldReturnAJoke() {
        //given
        JokeResponse response = JokeResponse.builder()
                .joke("When Chuck Norris throws exceptions, it’s across the room")
                .build();
        when(jokeService.getJoke()).thenReturn(response);
        //when
        mockMvc.perform(get("/v0/joke-endpoint"))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.joke", equalTo("When Chuck Norris throws exceptions, it’s across the room")));
    }

    @Test
    @SneakyThrows
    void whenJokeEndpointIsTriggered_andAnErrorOccurs_shouldReturnAJoke() {
        //given
        when(jokeService.getJoke()).thenThrow(new ChuckNorrisResponseException(Response.success(null).raw()));
        //when
        mockMvc.perform(get("/v0/joke-endpoint"))
                //then
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.url", equalTo("http://localhost/v0/joke-endpoint")))
                .andExpect(jsonPath("$.errorCode", equalTo(422)))
                .andExpect(jsonPath("$.exception", equalTo("Failed to get joke with Response{protocol=http/1.1, code=200, message=OK, url=http://localhost/}")));
    }

}
