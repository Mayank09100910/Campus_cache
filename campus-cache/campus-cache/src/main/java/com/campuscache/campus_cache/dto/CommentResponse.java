package com.campuscache.campus_cache.dto;

import java.time.LocalDateTime;
import lombok.Getter;
@Getter
public class CommentResponse {

    private Long id;
    private String text;
    private String commentedBy;
    private LocalDateTime createdAt;

    public CommentResponse(Long id, String text,
                           String commentedBy, LocalDateTime createdAt) {
        this.id = id;
        this.text = text;
        this.commentedBy = commentedBy;
        this.createdAt = createdAt;
    }

    // getters
}
