package com.sirius1b.auth;

import com.sirius1b.auth.services.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@AutoConfigureMockMvc
public class RunnerTest {

    @Autowired
    RedisService redisJsonService;


    @Test
    public void test1() throws Exception {
        redisJsonService.set("asdfasd", "asdfasdf");
        System.out.println(redisJsonService.get("asdfasd") + " " + "asdfasdfasdf");

    }
}
