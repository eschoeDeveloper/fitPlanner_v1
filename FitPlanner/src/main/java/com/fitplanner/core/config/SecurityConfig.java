package com.fitplanner.core.config;


import com.fitplanner.core.security.AuthenticationEntryPointJwt;
import com.fitplanner.core.security.AuthenticationJwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig{

    private final UserDetailsService userDetailsService;

    private final AuthenticationEntryPointJwt authenticationEntryPointJwt;

    @Bean("securityFilterChain")
    public SecurityFilterChain filterChain(HttpSecurity httpse) throws Exception {

        httpse.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPointJwt).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/api/**").authenticated()
                .anyRequest().authenticated();

        httpse.authenticationProvider(authProvider());
        httpse.addFilterBefore(authJwtFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpse.build();

    }

    @Bean("webSecurityCustomizer")
    public WebSecurityCustomizer webSecurityCustomizer() {

        return (web -> {
            web.ignoring().antMatchers(
               "/api/member/login",
               "/api/member/signup",
               "/api/member/checkId/**",
               "/api/member/auth"
            );
        });

    }

    @Bean("authJwtFilter")
    public AuthenticationJwtFilter authJwtFilter() {
        return new AuthenticationJwtFilter();
    }

    @Bean("authProvider")
    public DaoAuthenticationProvider authProvider() {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(userDetailsService);

        return authProvider;

    }

    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean("authenticationManager")
    public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
