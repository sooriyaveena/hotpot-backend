package com.hotpot.deliveryapplication.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http

            .cors(cors -> {})

            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                .requestMatchers(
                        "/users/login",
                        "/users/register"
                      
                ).permitAll()
                .requestMatchers(
    "/users/all",
    "/users/getallusers",
    "/users/*"
).hasRole("ADMIN")

                .requestMatchers(
                        "/restaurant/**",
                        "/menu/**",
                        "/category/**"
                ).permitAll()
 .requestMatchers(
                        "/restaurant/**",
                        "/menu/all"
                        
                ).permitAll()
                .requestMatchers(
                        "/order/all"
                ).permitAll()

                .requestMatchers(
                        "/users/getallusers"
                ).hasRole("ADMIN")

                .requestMatchers(
                        "/cart/**",
                        "/orders/place",
                        "/feedback/**"
                ).hasRole("USER")
                .requestMatchers(
                        "/cart/**",
                        
                        "/delivery/**"
                ).permitAll()

                .anyRequest().authenticated()
            );

        http.addFilterBefore(
                jwtFilter,
                UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration =
                new CorsConfiguration();

        configuration.setAllowedOrigins(
                List.of("http://localhost:3000")
        );

        configuration.setAllowedMethods(
                List.of(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "OPTIONS"
                )
        );

        configuration.setAllowedHeaders(
                List.of("*")
        );

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration(
                "/**",
                configuration
        );

        return source;
    }
}