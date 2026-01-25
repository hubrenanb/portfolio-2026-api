package com.renan.profile.core_api.controller; 

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Controlador
@RestController
// Endereço /api
@RequestMapping("/api")
public class HealthController {

    // Responde ao GET em /health
    @GetMapping("/health")
    public String checkAPI() {
        return "Portfólio 2026 API Online! 🚀";
    }
}