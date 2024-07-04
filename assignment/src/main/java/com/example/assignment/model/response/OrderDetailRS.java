package com.example.assignment.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailRS {
    private Long id;

    private Long orderId;

    private Long productId;

    private Integer quantity;

    private Long price;
}
