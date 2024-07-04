package com.example.assignment.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRQ {
    private Long id;

    @Length(max = 250)
    private String customerName;

    @Length(max = 250)
    private String deliveryAddress;

    @Length(max = 250)
    private String customerEmail;

    @Length(max = 50)
    private String customerPhone;

    List<OrderDetailRQ> orderDetails = new ArrayList<>();
}
