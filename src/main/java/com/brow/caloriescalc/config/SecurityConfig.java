package com.brow.caloriescalc.config;

import com.brow.caloriescalc.security.AuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/static/**", "/api/auth/signin")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/")
                        .loginProcessingUrl("/login")
                        .failureUrl("/?error=true")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/calc")
                        .permitAll()
                )
                .logout(logout -> logout
                                .permitAll()
//                ).exceptionHandling(ex -> ex
//                        .authenticationEntryPoint(new AuthenticationEntryPoint())
                );

        return http.build();
    }
}
