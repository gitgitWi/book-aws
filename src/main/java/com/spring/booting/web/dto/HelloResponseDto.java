package com.spring.booting.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 선언된 모든 필드의 getter 메소드 생성
@Getter
// 선언된 모든 final 필드가 포함된 생성자 자동생성; final 없는 필드는 제외
@RequiredArgsConstructor
public class HelloResponseDto {

    private final String name;
    private final int amount;
}
