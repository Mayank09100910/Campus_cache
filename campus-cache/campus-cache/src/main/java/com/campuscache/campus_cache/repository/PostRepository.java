package com.campuscache.campus_cache.repository;
import org.springframework.data.domain.Pageable;
import com.campuscache.campus_cache.entity.Post;
import com.campuscache.campus_cache.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByCreatedBy(user user);
    
    List<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
