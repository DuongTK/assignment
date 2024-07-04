package com.example.assignment.controller;

import com.example.assignment.model.filter.ProductFilter;
import com.example.assignment.model.request.ProductRQ;
import com.example.assignment.model.response.ProductRS;
import com.example.assignment.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/products/search")
    public ResponseEntity<Page<ProductRS>> search(@RequestBody ProductFilter filter) {
        Page<ProductRS> productRS = productService.search(filter);
        return ResponseEntity.ok(productRS);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductRS> getProductById(@PathVariable("productId") Long productId) {
       ProductRS rs = productService.getById(productId);
        return ResponseEntity.ok(rs);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductRS> createProduct(@Valid @RequestBody ProductRQ product) {
        ProductRS rs = productService.createProduct(product);
        return ResponseEntity.ok(rs);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<ProductRS> updateProduct(@PathVariable("productId") long productId,@Valid @RequestBody ProductRQ product) {
        product.setId(productId);
        ProductRS rs = productService.updateProduct(product);
        return ResponseEntity.ok(rs);
    }

    @DeleteMapping("/products/{productId}")
    public void deleteProduct(@PathVariable("productId") long productId) {
        productService.deleteById(productId);
    }
}

