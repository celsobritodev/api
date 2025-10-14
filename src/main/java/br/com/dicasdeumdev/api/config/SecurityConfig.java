package br.com.dicasdeumdev.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .headers(headers -> headers.frameOptions(frame -> frame.disable())) // <-- necessário para H2
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll() // <-- libera H2
                .anyRequest().permitAll()
            );

        return http.build();
    }
}