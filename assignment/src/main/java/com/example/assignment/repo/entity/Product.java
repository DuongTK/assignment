package com.example.assignment.repo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

@EqualsAndHashCode(callSuper=false)
@Data
@Entity
@Table(name = "products")
public class Product extends AbstractBaseEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Length(max = 500)
    private String name;

    @Length(max = 500)
    private String description;

    private Long price;

    private Long inventory;

    @Version
    private Long version;


}
