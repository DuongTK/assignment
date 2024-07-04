package com.example.assignment.repo.dao;

import com.example.assignment.repo.entity.Order;
import com.example.assignment.repo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

    @Query(value = "SELECT NEXTVAL('sequence_generator')", nativeQuery = true)
    Long getNextSequenceValue();

    @Query("SELECT p FROM Order p WHERE p.id = :id or p.customerName like '%:keywork%'")
    Page<Order> search(@Param("id") Long id,@Param("keywork") String keywork, Pageable pageable);
}
