package com.project.ecommerce_product_catalog.controller;

import com.project.ecommerce_product_catalog.model.Product;
import com.project.ecommerce_product_catalog.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CartController {
    private CartService cartService;
    public void CartController(CartService cartService) {
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
    @PostMapping("/cart/add/")
    public ResponseEntity<?> addCart(Product product){
        try{
            cartService.addCart(product);
            return new ResponseEntity<>("Product added successfully to the cart",HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Failed to add product to the cart",HttpStatus.CONFLICT);
        }
    }
    @PutMapping("/cart/update/{id}")
    public ResponseEntity<?> updateCart(@PathVariable int id,@RequestBody Product product){
        try{
            cartService.updateCart(id,product);
            return new ResponseEntity<>("cart updated successfully",HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Failed to update the cart",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/cart/removeitem/{cartid}")
    public ResponseEntity<?> deleteitem(@PathVariable int id){
        try{
            cartService.removeitem(id);
            return new ResponseEntity<>("Item Removed from the cart successfully",HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Failed to remove the item",HttpStatus.CONFLICT);
        }
    }
    @DeleteMapping("/cart/clear/{userid}")
    public ResponseEntity<?> clearcart(@PathVariable int id){
        try{
            cartService.clearcart(id);
            return new ResponseEntity<>("Cart cleared successfully",HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Failed to clear cart",HttpStatus.CONFLICT);
        }
    }

}
