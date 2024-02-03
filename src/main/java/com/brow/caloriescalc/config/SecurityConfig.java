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
        http.authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/", "/home", "/api/auth/signin")
//                        .permitAll()
//                        .requestMatchers("/api/**")
//                        .authenticated()
                                .anyRequest()
                                .permitAll()
                )
//                .formLogin(login -> login
//                        .loginPage("/login")
//                        .permitAll()
//                )
                .logout(logout -> logout
                        .permitAll()
                        .logoutSuccessUrl("/home")
//                ).exceptionHandling(ex -> ex
//                        .authenticationEntryPoint(new AuthenticationEntryPoint())
                ).csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
