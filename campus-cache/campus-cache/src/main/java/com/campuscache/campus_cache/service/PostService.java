package com.campuscache.campus_cache.service;

import com.campuscache.campus_cache.dto.PostRequest;
import com.campuscache.campus_cache.dto.PostResponse;
import com.campuscache.campus_cache.entity.Post;
import com.campuscache.campus_cache.entity.user;
import com.campuscache.campus_cache.repository.PostRepository;
import com.campuscache.campus_cache.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepo;
    private final UserRepository userRepo;

    public PostService(PostRepository postRepo, UserRepository userRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    public PostResponse createPost(PostRequest request, Authentication auth) {

        String email = auth.getName();
        user user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setDescription(request.getDescription());
        post.setCreatedBy(user);
        post.setCreatedAt(LocalDateTime.now());

        Post saved = postRepo.save(post);

        return new PostResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getDescription(),
                user.getName(),
                saved.getCreatedAt()
        );
    }
public List<PostResponse> getAllPosts(int page, int size) {

    return postRepo.findAllByOrderByCreatedAtDesc(PageRequest.of(page, size))
            .stream()
            .map(p -> new PostResponse(
                    p.getId(),
                    p.getTitle(),
                    p.getDescription(),
                    p.getCreatedBy().getName(),
                    p.getCreatedAt()
            ))
            .toList();
}
public void deletePost(Long postId, Authentication auth) {

    String email = auth.getName();

    user user = userRepo.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    Post post = postRepo.findById(postId)
            .orElseThrow(() -> new RuntimeException("Post not found"));

    // 🔐 OWNERSHIP CHECK
    if (!post.getCreatedBy().getId().equals(user.getId())) {
        throw new RuntimeException("You are not allowed to delete this post");
    }

    postRepo.delete(post);
}

    public List<PostResponse> getMyPosts(Authentication auth) {

        user user = userRepo.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return postRepo.findByCreatedBy(user)
                .stream()
                .map(p -> new PostResponse(
                        p.getId(),
                        p.getTitle(),
                        p.getDescription(),
                        user.getName(),
                        p.getCreatedAt()
                ))
                .toList();
    }
}
