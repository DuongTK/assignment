package com.example.assignment.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRQ {

    private Long id;
    @NotNull
    @Length(max = 500)
    private String name;

    @Length(max = 500)
    private String description;
    private Long price;
    private Long inventory;
}
