package io.sf.mvntemplate.domain.service.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JokeDTOUnmarshallingTest {

    @Test
    @SneakyThrows
    void whenJsonIsValid_shouldMapItSuccessfullyToJoke() {
        //when
        JokeDTO joke = unmarshall("src/test/resources/successful_joke.json");
        //then
        assertNotNull(joke);
        assertEquals("Chuck Norris taught Albert Einstein e=mc^2", joke.value);
    }

    @Test
    @SneakyThrows
    void whenJsonMissesMandatoryData_shouldToMapItToNull() {
        //when
        JokeDTO joke = unmarshall("src/test/resources/defective_joke.json");
        //then
        assertNotNull(joke);
        assertNull(joke.value);
    }

    @Test
    @SneakyThrows
    void whenJsonIsEmpty_shouldNotFailAndToMapItToNull() {
        //when
        JokeDTO joke = unmarshall("src/test/resources/empty_joke.json");
        //then
        assertNotNull(joke);
        assertNull(joke.value);
        assertNull(joke.id);
        assertNull(joke.url);
    }

    private JokeDTO unmarshall(String filename) throws IOException {
        ObjectMapper jsonMapper = new JsonMapper();
        return jsonMapper.readValue(new File(filename), JokeDTO.class);
    }

}