package com.project.ecommerce_product_catalog.controller;

import com.project.ecommerce_product_catalog.dto.ProductDTO;
import com.project.ecommerce_product_catalog.model.Product;
import com.project.ecommerce_product_catalog.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<Page<Product>> showProducts(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "5") int size,
                                                      @RequestParam(defaultValue = "name") String sortBy){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        Page<Product> products=service.showProducts(pageable);
        if(products==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.FOUND);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Product product=service.getProductById(id);
        if(product==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product,HttpStatus.FOUND);
    }
    @GetMapping("/category")
    public ResponseEntity<List<Product>> getByCategory(@RequestParam String category){
        try{
            List<Product> product=service.getProductByCategory(category);
            return new ResponseEntity<>(product,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/addproduct")
    public ResponseEntity<String> addProduct(@Valid @RequestBody Product product){
        try{
            service.addProduct(product);
            return new ResponseEntity<>("Product added successfully",HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>("Product couldn't addded",HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping("update/{id}")
    public ResponseEntity<String> updateProduct(@Valid @RequestBody ProductDTO dto, @PathVariable Long id){
        try{
            service.updateProduct(id,dto);
            return new ResponseEntity<>("Product updated successfully",HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Product is not updated",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        try{
            service.deleteProduct(id);
            return new ResponseEntity<>("Product deleted Successfully",HttpStatus.OK);
        }
        catch (Exception e){
            return  new ResponseEntity<>("Product not deleted",HttpStatus.NOT_FOUND);
        }
    }

    

}
