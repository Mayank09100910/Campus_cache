package com.campuscache.campus_cache.repository;
import java.util.Optional;
import com.campuscache.campus_cache.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<user, Long> {
    Optional<user> findByEmail(String email);
}