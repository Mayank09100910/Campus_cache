package com.campuscache.campus_cache.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(unique = true, nullable = false)
private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
