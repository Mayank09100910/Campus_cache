package com.campuscache.campus_cache.dto;

import java.time.LocalDateTime;
import lombok.Getter;
@Getter
public class PostResponse {

    private Long id;
    private String title;
    private String description;
    private String createdBy;
    private LocalDateTime createdAt;

    public PostResponse(Long id, String title, String description,
                        String createdBy, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    // getters
}
