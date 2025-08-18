package com.project.ecommerce_product_catalog.controller;

import com.project.ecommerce_product_catalog.model.Product;
import com.project.ecommerce_product_catalog.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CartController {
    private CartService cartService;
    public  CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @GetMapping("/cart/{userId}")
    public ResponseEntity<?> getCartItems(@PathVariable Long userId) {
        try{
            List<Product> products=cartService.getProducts(userId);
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Failed to get the products",HttpStatus.CONFLICT);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/cart/add/{id}")
    public ResponseEntity<?> addCart(@PathVariable Long id,@RequestBody Product product){
        try{
            Long productid= product.getId();
            cartService.addCart(id,productid);
            return new ResponseEntity<>("Product added successfully to the cart",HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Failed to add product to the cart",HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/cart/removeitem/{userid}")
    public ResponseEntity<?> deleteitem(@PathVariable Long userid,@RequestParam Long prodid){
        try{
            cartService.removeitem(userid,prodid);
            return new ResponseEntity<>("Item Removed from the cart successfully",HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Failed to remove the item",HttpStatus.CONFLICT);
        }
    }
    @DeleteMapping("/cart/clear/{userid}")
    public ResponseEntity<?> clearcart(@PathVariable Long id){
        try{
            cartService.clearcart(id);
            return new ResponseEntity<>("Cart cleared successfully",HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Failed to clear cart",HttpStatus.CONFLICT);
        }
    }

}
