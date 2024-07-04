package com.example.assignment.service;

import com.example.assignment.model.filter.ProductFilter;
import com.example.assignment.model.request.ProductRQ;
import com.example.assignment.model.response.ProductRS;
import org.springframework.data.domain.Page;

public interface ProductService {
    ProductRS createProduct(ProductRQ product);
    ProductRS updateProduct(ProductRQ product);
    ProductRS getById(Long productId);
    void deleteById(Long productId);
    Page<ProductRS> search(ProductFilter filter);


}
