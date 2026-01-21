package com.campuscache.campus_cache.controller;
import com.campuscache.campus_cache.dto.LoginRequest;
import com.campuscache.campus_cache.dto.RegisterRequest;
import com.campuscache.campus_cache.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }
    
@PostMapping("/login")
public String login(@RequestBody LoginRequest request) {
    return authService.login(request);
}
}
