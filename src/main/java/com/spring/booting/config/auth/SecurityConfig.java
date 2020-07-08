package com.spring.booting.config.auth;

import com.spring.booting.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure (HttpSecurity http) throws Exception {
        // h2-console 화면을 사용하기 위해 해당 옵션들을 disable
        http.csrf().disable().headers().frameOptions().disable()
            .and()
                // URL별 권환 관리 설정하는 option 시작점
                .authorizeRequests()
                // 권한 관리 대상 지정하는 옵션, URL/HTTP 메소드별로 관리 가능
                // permitAll() : 전체 열람 권한
                // hasRole(Role.USER.name()) : USER 권한 가진 사용자만 가능
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                // 설정된 값 이외 나머지 url들
                // authenticated() : 인증된 사용자만 허가
                .anyRequest().authenticated()
            .and()
                // 로그아웃 설정, 로그아웃 성공시 baseurl로 이동
                .logout().logoutSuccessUrl("/")
            .and()
                // OAuth2 로그인 설정
                // userInfoEndpoint() : 로그인 성공 시 사용자 정보 가져 올 때
                // userService(customOAuth2UserService) : UserService 인터페이스의 구현체를 CustomOAuth2UserService로 등록
                .oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);
    }
}

