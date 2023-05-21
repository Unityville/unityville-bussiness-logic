package com.example.Unityville.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class ConfigSecurity {

    @Value("${jwtKeySet}")
    private String key;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new GrantedAuthoritiesExtractor());

        httpSecurity.oauth2ResourceServer(
                r -> r.jwt()
                        .jwtAuthenticationConverter(jwtAuthenticationConverter)
                        .jwkSetUri(key)
        );
        httpSecurity.csrf().disable().authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/posts").hasAuthority("admin")
                .requestMatchers(HttpMethod.DELETE, "/posts/*").hasAuthority("admin")
                .requestMatchers(HttpMethod.PUT, "/posts/**").hasAuthority("admin")
                .anyRequest().authenticated();
        return httpSecurity.build();
    }

    public static class GrantedAuthoritiesExtractor implements Converter<Jwt, Collection<GrantedAuthority>> {
        @Override
        public Collection<GrantedAuthority> convert(Jwt jwt) {
            List<String> roles = jwt.getClaim("roles");
            return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }
    }
}
