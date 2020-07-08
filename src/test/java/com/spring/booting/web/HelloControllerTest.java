package com.spring.booting.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Spring Boot Test와 JUnit 사이 연결자 역할 ; JUnit에 내장된 실행자 외 다른 실행자 실행, 여기서는 SpringRunner라는 실행자 실행
@RunWith(SpringRunner.class)
// Web(Spring MVC)에 집중하는 annotation, @Controller, @ControllerAdvice 등 사용, @Service, @Component, @Repository 등 사용 불가
@WebMvcTest
public class HelloControllerTest {
    
//    Spring Bean 주입
//    Spring MVC test 시작점, GET, POST 등 web API test
    @Autowired
    private MockMvc mvc;

    @Test
    public void return_Hello() throws Exception {
        String hello = "hello";

//       perform(get("/hello")) : /hello 주소로 GET request
//       andExpect() : 결과 검증
        mvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string(hello));
    }

    @Test
    public void return_HelloDto() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                    .param("name", name)
                    .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
//                jsonPath("$.name", is(name)) ; $ 기준으로 필드면 명시
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));

    }
}
