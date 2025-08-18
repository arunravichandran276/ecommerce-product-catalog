package com.project.ecommerce_product_catalog.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Size(max = 20,min=1)
    @Column(nullable = false,unique = true)
    private String username;
    private String firstName;
    private String LastName;
    private String email;
//    @Size(max=12,min=8)
    private String password;
    private String role;
    private LocalDateTime createdAt;
    private Boolean isActive=true;
    private String phoneNumber;
    private String profilePicture;

    public User(Long userId) {
        this.id=userId;
    }
}
