package com.renan.profile.core_api.controller; 

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 1. AQUI: Dizemos que é um controlador
@RestController
// 2. AQUI: Dizemos que o endereço começa com /api
@RequestMapping("/api")
public class HealthController {

    // 3. AQUI: Dizemos que responde ao GET em /health
    @GetMapping("/health")
    public String checkAPI() {
        return "Portfólio 2026 API Online! 🚀";
    }
}