package com.spring.booting.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void test_for_lombok() {
        String name = "test";
        int amount = 1000;

        HelloResponseDto dto = new HelloResponseDto(name, amount);

//        assertThat () : 테스트 검증 메소드, 여기서는 JUnit의 기본 assertThat이 아닌 assertj의 것 사용, 
//        CoreMatchers와 달리 추가적인 라이브러리 필요 없음, 자동완성이 좀 더 확실하게 지원됨
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
