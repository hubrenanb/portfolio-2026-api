package com.renan.profile.core_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry; // <--- Import Novo
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 1. Configuração de CORS (Mantivemos a sua, que está ótima)
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }

    // 2. Configuração de Arquivos Estáticos (A PEÇA QUE FALTAVA)
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Tradução: "Quando alguém pedir http://.../uploads/foto.jpg, entregue o arquivo da pasta uploads"
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}