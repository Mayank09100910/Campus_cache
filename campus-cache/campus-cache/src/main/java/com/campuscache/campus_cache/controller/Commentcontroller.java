package com.campuscache.campus_cache.controller;
import com.campuscache.campus_cache.dto.CommentRequest;
import com.campuscache.campus_cache.dto.CommentResponse;

import com.campuscache.campus_cache.service.CommentService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class Commentcontroller {

    private final CommentService commentService;

    public Commentcontroller(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}")
    public CommentResponse addComment(@PathVariable Long postId,
                                      @RequestBody CommentRequest request,
                                      Authentication auth) {
        return commentService.addComment(postId, request, auth);
    }

    @GetMapping("/{postId}")
    public List<CommentResponse> getComments(@PathVariable Long postId) {
        return commentService.getComments(postId);
    }
    @DeleteMapping("/{commentId}")
public String deleteComment(@PathVariable Long commentId,
                            Authentication auth) {

    commentService.deleteComment(commentId, auth);
    return "Comment deleted successfully";
}

}
