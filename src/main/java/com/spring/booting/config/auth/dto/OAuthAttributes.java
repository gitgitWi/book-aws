package com.spring.booting.config.auth.dto;

import com.spring.booting.domain.user.Role;
import com.spring.booting.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    // OAuth2User에서 반환하는 Map 타입 사용자 정보를 하나씩 변환
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                    .name( (String) attributes.get("name"))
                    .email( (String) attributes.get("email"))
                    .picture( (String) attributes.get("picture"))
                    .attributes(attributes)
                    .nameAttributeKey(userNameAttributeName)
                    .build();
    }

    // 처음 가입시 entity 생성
    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST) // 기본권한 GUEST
                .build();
    }
}
