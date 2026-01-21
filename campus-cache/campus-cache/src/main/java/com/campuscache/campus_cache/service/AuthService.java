package com.campuscache.campus_cache.service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.campuscache.campus_cache.dto.LoginRequest;
import com.campuscache.campus_cache.security.JwtUtil;
import com.campuscache.campus_cache.dto.RegisterRequest;
import com.campuscache.campus_cache.entity.Role;
import com.campuscache.campus_cache.entity.user;
import com.campuscache.campus_cache.repository.RoleRepository;
import com.campuscache.campus_cache.repository.UserRepository;
import org.springframework.stereotype.Service;
import lombok.*;
@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
private final PasswordEncoder passwordEncoder;
private final JwtUtil jwtUtil;



public String login(LoginRequest request) {
    user user = userRepo.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        throw new RuntimeException("Invalid password");
    }
    return jwtUtil.generateToken(user.getEmail());
}
  

public String register(RegisterRequest request) {
if (userRepo.findByEmail(request.getEmail()).isPresent()) {
    throw new RuntimeException("Email already registered");
}

        // role find ya create
        Role role = roleRepo.findAll().stream()
                .filter(r -> r.getName().equals(request.getRole()))
                .findFirst()
                .orElseGet(() -> roleRepo.save(new Role(null, request.getRole())));

        user user = new user();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
       user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(role);

        userRepo.save(user);
        return "User registered successfully";
    }
}

