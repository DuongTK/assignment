package com.example.assignment.service.impl;

import com.example.assignment.model.exception.BaseException;
import com.example.assignment.model.exception.ExceptionConstant;
import com.example.assignment.model.filter.ProductFilter;
import com.example.assignment.model.request.ProductRQ;
import com.example.assignment.model.response.ProductRS;
import com.example.assignment.repo.dao.ProductRepo;
import com.example.assignment.repo.entity.Product;
import com.example.assignment.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpt implements ProductService {

    private final ProductRepo productRepo;
    private final ModelMapper mapper;

    @Override
    public ProductRS createProduct(ProductRQ productRQ) {

        Product product = new Product();
        product.setName(productRQ.getName());
        product.setDescription(productRQ.getDescription());
        product.setPrice(productRQ.getPrice());
        product.setInventory(productRQ.getInventory());

        Product rs = productRepo.save(product);
        return mapper.map(rs, ProductRS.class);
    }

    @Override
    public ProductRS updateProduct(ProductRQ productRQ) {
        Product product = productRepo.findById(productRQ.getId()).orElseThrow(() -> new BaseException(ExceptionConstant.PRODUCT_NOT_FOUND));
        product.setDescription(productRQ.getDescription());
        product.setPrice(productRQ.getPrice());
        product.setName(productRQ.getName());
        product.setInventory(productRQ.getInventory());
        productRepo.save(product);
        return mapper.map(product, ProductRS.class);

    }

    @Override
    public ProductRS getById(Long productId) {
        Product product = productRepo.findById(productId).orElseThrow(() -> new BaseException(ExceptionConstant.PRODUCT_NOT_FOUND));
        return mapper.map(product, ProductRS.class);
    }

    @Override
    public void deleteById(Long productId) {
        Product product = productRepo.findById(productId).orElseThrow(() -> new BaseException(ExceptionConstant.PRODUCT_NOT_FOUND));
        productRepo.deleteById(productId);
    }

    @Override
    public Page<ProductRS> search(ProductFilter filter) {
        Page<Product> products = productRepo.search(filter.getKeywork(), PageRequest.of(filter.getPage(), filter.getLimit()));
        List<ProductRS> productRS = products.stream().map(p -> mapper.map(p, ProductRS.class)).toList();
        return new PageImpl<>(productRS);
    }
}
