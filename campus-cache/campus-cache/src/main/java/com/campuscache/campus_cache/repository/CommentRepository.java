package com.campuscache.campus_cache.repository;

import com.campuscache.campus_cache.entity.Comment;
import com.campuscache.campus_cache.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPost(Post post);
}
