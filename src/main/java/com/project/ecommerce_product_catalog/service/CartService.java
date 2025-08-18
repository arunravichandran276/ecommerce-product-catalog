package com.project.ecommerce_product_catalog.service;

import com.project.ecommerce_product_catalog.exception.ResourceNotFoundException;
import com.project.ecommerce_product_catalog.model.Cart;
import com.project.ecommerce_product_catalog.model.CartItem;
import com.project.ecommerce_product_catalog.model.Product;
import com.project.ecommerce_product_catalog.model.User;
import com.project.ecommerce_product_catalog.repository.CartRepository;
import com.project.ecommerce_product_catalog.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
     private ProductRepository productRepository;

    private CartRepository repository;
    public  CartService(CartRepository repository){
        this.repository=repository;
    }
    private final Logger logger= LoggerFactory.getLogger(CartService.class);
    public List<Product> getProducts(Long userId) {
        try {
            Cart cart = repository.findByUser_Id(userId).orElseThrow(() -> new RuntimeException("failed to get products"));
            List<Product> products=new ArrayList<>();
            for(CartItem item: cart.getCartItems()){
                products.add(item.getProduct());
            }
            return products;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCart(Long userId, Long productId) {
        logger.info("Trying to add product {} to cart for user {}", productId, userId);

        // Step 1: Find cart or create a new one
        Cart cart = repository.findByUser_Id(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    User user=new User();
                    user.setId(userId);
                    newCart.setUser(user); // assuming constructor or setter exists
                    newCart.setCartItems(new ArrayList<>());
                    return repository.save(newCart);
                });

        // Step 2: Fetch product from DB
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + productId));

        // Step 3: Add product as CartItem
        CartItem item = new CartItem();
        item.setProduct(product);
        item.setQuantity(1);
        item.setCart(cart);

        cart.getCartItems().add(item);
        repository.save(cart);

        logger.info("Product {} added successfully to cart for user {}", productId, userId);
    }



    public void removeitem(Long userid,Long productId) {
        Cart cart=repository.findByUser_Id(userid).orElseThrow(()->new RuntimeException("No cart found for the user"));
        cart.getCartItems().removeIf(item -> item.getProduct().getId().equals(productId));
        repository.save(cart);
    }

    public void clearcart(Long id) {
        try{
            Cart cart=repository.findByUser_Id(id).orElseThrow(()->new RuntimeException("No cart found for the user"));
            cart.getCartItems().clear();
            repository.save(cart);
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to clear cart"+e);
        }
    }
}
