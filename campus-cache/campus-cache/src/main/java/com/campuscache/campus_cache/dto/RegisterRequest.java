package com.campuscache.campus_cache.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String role; // STUDENT / FACULTY / ADMIN
}
