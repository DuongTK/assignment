package com.example.assignment.controller;

import com.example.assignment.model.filter.OrderFilter;
import com.example.assignment.model.filter.ProductFilter;
import com.example.assignment.model.request.OrderRQ;
import com.example.assignment.model.request.ProductRQ;
import com.example.assignment.model.response.OrderRS;
import com.example.assignment.model.response.ProductRS;
import com.example.assignment.service.OrderService;
import com.example.assignment.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/orders/search")
    public ResponseEntity<Page<OrderRS>> search(@RequestBody OrderFilter filter) {
        Page<OrderRS> orderRS = orderService.search(filter);
        return ResponseEntity.ok(orderRS);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderRS> getById(@PathVariable("orderId") Long orderId) {
        OrderRS rs = orderService.getById(orderId);
        return ResponseEntity.ok(rs);
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderRS> createOrder(@Valid @RequestBody OrderRQ orderRQ) {
        OrderRS rs = orderService.createOrder(orderRQ);
        return ResponseEntity.ok(rs);
    }

    @PutMapping("/orders/{orderId}")
    public ResponseEntity<OrderRS> updateOrder(@PathVariable("orderId") long orderId,@Valid @RequestBody OrderRQ orderRQ) {
        orderRQ.setId(orderId);
        OrderRS rs = orderService.updateOrder(orderRQ);
        return ResponseEntity.ok(rs);
    }

    @DeleteMapping("/orders/{orderId}")
    public void deleteById(@PathVariable("orderId") long orderId) {
        orderService.deleteById(orderId);
    }
}
