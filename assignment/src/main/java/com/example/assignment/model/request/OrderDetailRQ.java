package com.example.assignment.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailRQ {
    private Long id;

    private Long productId;

    private Integer quantity;
}
