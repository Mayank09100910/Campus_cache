package com.campuscache.campus_cache.service;

import com.campuscache.campus_cache.dto.CommentRequest;
import com.campuscache.campus_cache.dto.CommentResponse;
import com.campuscache.campus_cache.entity.Comment;
import com.campuscache.campus_cache.entity.Post;
import com.campuscache.campus_cache.entity.user;
import com.campuscache.campus_cache.repository.CommentRepository;
import com.campuscache.campus_cache.repository.PostRepository;
import com.campuscache.campus_cache.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepo;
    private final PostRepository postRepo;
    private final UserRepository userRepo;

    public CommentService(CommentRepository commentRepo,
                          PostRepository postRepo,
                          UserRepository userRepo) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    public CommentResponse addComment(Long postId,
                                      CommentRequest request,
                                      Authentication auth) {

        user user = userRepo.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        Comment comment = new Comment();
        comment.setText(request.getText());
        comment.setPost(post);
        comment.setUser(user);
        comment.setCreatedAt(LocalDateTime.now());

        Comment saved = commentRepo.save(comment);

        return new CommentResponse(
                saved.getId(),
                saved.getText(),
                user.getName(),
                saved.getCreatedAt()
        );
    }
public void deleteComment(Long commentId, Authentication auth) {

    String email = auth.getName();

    user user = userRepo.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));

    Comment comment = commentRepo.findById(commentId)
            .orElseThrow(() -> new RuntimeException("Comment not found"));

    // 🔐 OWNERSHIP CHECK
    if (!comment.getUser().getId().equals(user.getId())) {
        throw new RuntimeException("You are not allowed to delete this comment");
    }

    commentRepo.delete(comment);
}

    public List<CommentResponse> getComments(Long postId) {

        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        return commentRepo.findByPost(post)
                .stream()
                .map(c -> new CommentResponse(
                        c.getId(),
                        c.getText(),
                        c.getUser().getName(),
                        c.getCreatedAt()
                ))
                .toList();
    }
}
