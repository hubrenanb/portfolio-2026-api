package com.renan.profile.core_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                    "http://localhost:5173",                    // Seu ambiente local
                    "https://portfolio-2026-front.vercel.app",  // Link da Vercel
                    "https://www.renanbernardo.com.br",         // Seu domínio oficial
                    "https://renanbernardo.com.br"              // Domínio sem www
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }
}