package com.campuscache.campus_cache.controller;

import com.campuscache.campus_cache.dto.PostRequest;
import com.campuscache.campus_cache.dto.PostResponse;
import com.campuscache.campus_cache.service.PostService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public PostResponse createPost(@RequestBody PostRequest request,
                                   Authentication auth) {
        return postService.createPost(request, auth);
    }

    @GetMapping("/me")
    public List<PostResponse> myPosts(Authentication auth) {
        return postService.getMyPosts(auth);
    }
    @GetMapping
public List<PostResponse> getAllPosts(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size) {

    return postService.getAllPosts(page, size);
}
@DeleteMapping("/{postId}")
public String deletePost(@PathVariable Long postId,
                         Authentication auth) {

    postService.deletePost(postId, auth);
    return "Post deleted successfully";
}

}
