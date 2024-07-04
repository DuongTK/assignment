package com.example.assignment.repo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
@Entity
@Table(name = "order_details")
public class OrderDetail extends AbstractBaseEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    private Long orderId;

    private Long productId;

    private Integer quantity;

    private Long price;
}
