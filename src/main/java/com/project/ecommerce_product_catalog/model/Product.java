package com.project.ecommerce_product_catalog.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max =100)
    private String name;
    private String description;
    @Min(0)
    private Double price;
    @Min(1)
    private Integer quantity;
    private String category;
    private String imageurl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status ;
    @ElementCollection
    private List<String> tags;

}
