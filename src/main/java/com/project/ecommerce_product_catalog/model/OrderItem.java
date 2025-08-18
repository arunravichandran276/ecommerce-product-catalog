package com.project.ecommerce_product_catalog.model;


import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Belongs to an order
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // Refers to a product
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;
    private double price; // store price at time of purchase

}