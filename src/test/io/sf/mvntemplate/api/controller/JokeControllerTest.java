package io.sf.mvntemplate.api.controller;

import io.sf.mvntemplate.domain.service.JokeService;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

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
        when(jokeService.getJoke()).thenReturn("When Chuck Norris throws exceptions, it’s across the room");
        //when
        mockMvc.perform(get("/v0/joke-endpoint"))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", equalTo("When Chuck Norris throws exceptions, it’s across the room")));
    }

}
