package com.example.Unityville.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfigSecurity {

    @Value("${jwtKeySet}")
    private String key;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.oauth2ResourceServer(
                r -> r.jwt().jwkSetUri(key)
        );
        httpSecurity.csrf().disable().authorizeHttpRequests()
//                .requestMatchers("/users/**").hasRole("user")
                .anyRequest().authenticated();
        return httpSecurity.build();
    }
}
