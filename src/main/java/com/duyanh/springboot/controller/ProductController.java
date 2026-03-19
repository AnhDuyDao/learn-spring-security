package com.duyanh.springboot.controller;

import com.duyanh.springboot.model.Product;
import com.duyanh.springboot.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public Long createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productService.findAll();
    }
}
