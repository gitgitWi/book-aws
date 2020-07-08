package com.spring.booting.domain.posts;

import com.spring.booting.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

// Entity Class에서는 Setter 절대 생성하지 않음
// 해당 클래스의 인스턴스 값들이 언제 어디서 변해야 하는지 코드상으로 명확히 구분 불가능하므로
// 대신 생성자를 통해 삽입하거나, @Builder 사용
@Getter
@NoArgsConstructor // 기본 생성자 자동추가
@Entity // DB Table과 링크될 클래스임을 선언; 기본적으로 클래스 - 카멜케이스 스타일, 테이블 - 언더스코어 스타일로 매칭
public class Posts extends BaseTimeEntity {

    @Id // 해당 Table의 Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙, IDENTITY 추가해야 auto-increment
    private Long id;

    @Column(length = 500, nullable = false) 
    // Table Column, 굳이 안써도 해당 클래스의 모든 필드는 기본적으로 컬럼으로 인식. 
    // varchar의 length 기본값은 255, 여기서  500으로 늘림
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
