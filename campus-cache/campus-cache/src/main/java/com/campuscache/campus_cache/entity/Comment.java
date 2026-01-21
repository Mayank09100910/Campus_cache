package com.campuscache.campus_cache.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;
@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String text;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private user user;

    private LocalDateTime createdAt;

    // getters & setters
}
