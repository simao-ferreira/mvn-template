package io.sf.mvntemplate.domain.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JokeServiceImpl implements JokeService {

    @Resource
    private ChuckNorrisClient chuckNorrisClient;

    @Override
    public String getJoke() {
        return chuckNorrisClient.getChuckNorrisJoke().getValue();
    }


}
