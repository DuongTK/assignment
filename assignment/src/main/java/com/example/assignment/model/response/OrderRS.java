package com.example.assignment.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRS {
    private Long id;

    private String customerName;

    private String deliveryAddress;

    private String customerEmail;

    private String customerPhone;

    private String status;

    private Long orderAmount;

    List<OrderDetailRS> orderDetails;
}
