package com.project.ecommerce_product_catalog.controller;

import com.project.ecommerce_product_catalog.model.Product;
import com.project.ecommerce_product_catalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService service;
    @GetMapping("/")
    public  String home(){
        return "Welcome  to my website";
    }

    @GetMapping("/allproducts")
    public ResponseEntity<List<Product>> showProducts(){
        List<Product> products= service.showProducts();
        if(products==null){
            return new ResponseEntity<>(products,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.FOUND);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        Product product=service.getProductById(id);
        if(product==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product,HttpStatus.FOUND);
    }
    @PostMapping("/addproduct")
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        try{
            service.addProduct(product);
            return new ResponseEntity<>("Product added successfully",HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>("Product couldn't addded",HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("update/{id}")
    public ResponseEntity<String> updateProduct(@RequestBody Product product,@PathVariable int id){
        try{
            service.updateProduct(id,product);
            return new ResponseEntity<>("Product updated successfully",HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Product is not updated",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        try{
            service.deleteProduct(id);
            return new ResponseEntity<>("Product deleted Successfully",HttpStatus.OK);
        }
        catch (Exception e){
            return  new ResponseEntity<>("Product not deleted",HttpStatus.NOT_FOUND);
        }
    }

    

}
