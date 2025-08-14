package com.project.ecommerce_product_catalog.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String username;
    private String email;
//    @Size(max=12,min=8)
    private String password;
    private String role;
    private LocalDateTime createdAt;
    private Boolean isActive=true;
    private String phoneNumber;
    private String profilePicture;
}
