package com.example.assignment.repo.dao;

import com.example.assignment.repo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.name like '%:keywork%' or p.description like '%:keywork%'")
    Page<Product> search(@Param("keywork") String keywork, Pageable pageable);
}
