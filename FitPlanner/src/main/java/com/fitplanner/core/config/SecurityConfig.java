package com.fitplanner.core.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig{

    @Bean("securityFilterChain")
    public SecurityFilterChain filterChain(HttpSecurity httpse) throws Exception {

        httpse.formLogin().disable();

        httpse.csrf().disable();		// 개발 시 에만

//        httpse.authorizeRequests()
//                .antMatchers("/api/**").authenticated();

        return httpse.build();

    }

    @Bean("webSecurityCustomizer")
    public WebSecurityCustomizer webSecurityCustomizer() {

        return (web -> {
            web.ignoring().antMatchers("/api/member/**");
        });

    }

    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncode() {
        return new BCryptPasswordEncoder();
    }

    @Bean("authenticationManager")
    public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
