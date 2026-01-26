package com.renan.profile.core_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KeepAliveController {

    @GetMapping("/ping")
    public String ping() {
        return "Pong! O servidor está acordado. ☕";
    }
}