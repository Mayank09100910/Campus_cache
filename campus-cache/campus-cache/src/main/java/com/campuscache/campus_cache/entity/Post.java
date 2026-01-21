package com.campuscache.campus_cache.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;
@Entity
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private user createdBy;

    private LocalDateTime createdAt;

    // getters & setters
}
