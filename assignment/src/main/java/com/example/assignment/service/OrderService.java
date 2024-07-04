package com.example.assignment.service;

import com.example.assignment.model.filter.OrderFilter;
import com.example.assignment.model.request.OrderRQ;
import com.example.assignment.model.response.OrderRS;
import org.springframework.data.domain.Page;

public interface OrderService {
    OrderRS createOrder(OrderRQ orderRQ);
    OrderRS updateOrder(OrderRQ orderRQ);
    OrderRS getById(Long orderId);
    void deleteById(Long orderId);
    Page<OrderRS> search(OrderFilter filter);
}
