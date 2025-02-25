package com.example.assignment.repo.dao;

import com.example.assignment.repo.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findByOrderId(Long orderId);
}
