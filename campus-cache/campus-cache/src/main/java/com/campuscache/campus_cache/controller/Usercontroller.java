package com.campuscache.campus_cache.controller;
import com.campuscache.campus_cache.dto.UserResponse;
import com.campuscache.campus_cache.entity.user;
import com.campuscache.campus_cache.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class Usercontroller {

    private final UserRepository userRepo;

    public Usercontroller(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

   @GetMapping("/me")
public UserResponse getCurrentuser(Authentication authentication) {

    String email = authentication.getName();

    user user = userRepo.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    return new UserResponse(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getRole().getName()
    );
}

}
