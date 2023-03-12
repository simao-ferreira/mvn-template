package io.sf.mvntemplate.domain.service;

import io.sf.mvntemplate.domain.service.model.JokeDTO;
import retrofit2.Call;
import retrofit2.http.GET;


public interface ChuckNorrisConnector {

    @GET("jokes/random")
    Call<JokeDTO> getJoke();
}
