package com.example.assignment.repo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

@EqualsAndHashCode(callSuper=false)
@Data
@Entity
@Table(name = "orders")
public class Order extends AbstractBaseEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Length(max = 250)
    private String customerName;

    @Length(max = 250)
    private String deliveryAddress;

    @Length(max = 250)
    private String customerEmail;

    @Length(max = 50)
    private String customerPhone;

    @Length(max = 100)
    private String status;

    private Long orderAmount;
}
