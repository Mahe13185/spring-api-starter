package com.codewithmosh.store.controller;

import com.codewithmosh.store.Mappers.ProductMapper;
import com.codewithmosh.store.dtos.ProductDto;
import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @GetMapping
    List<ProductDto> findAllProducts(@RequestParam(required = false , defaultValue = "" , name = "categoryId") Byte categoryId) {
        List<Product> products;

        if (categoryId != null)
            products = productRepository.findAllByCategoryId(categoryId);
        else
            products = productRepository.findAllByCategoryId();


       return products
               .stream()
               .map(productMapper::toDto)
               .toList();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findProductById(@PathVariable Long id) {
        var product = productRepository.findById(id).orElse(null);

        if (product == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(productMapper.toDto(product));
    }
}
