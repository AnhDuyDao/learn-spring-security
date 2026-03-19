package com.duyanh.springboot.service;

import com.duyanh.springboot.model.Product;
import com.duyanh.springboot.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Long createProduct(Product product) {
        Product save = productRepository.save(product);
        return save.getId();
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

}
