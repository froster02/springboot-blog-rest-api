package com.springboot.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable()) // Disables CSRF using the new lambda-based API
                .authorizeHttpRequests(authorize ->
                        authorize.anyRequest().authenticated() // Requires authentication for any HTTP request
                )
                .httpBasic(Customizer.withDefaults()); // Enables basic auth with default config

        return http.build();
    }
}
