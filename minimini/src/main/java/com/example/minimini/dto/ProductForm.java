package com.example.minimini.dto;

import com.example.minimini.entity.Product;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ProductForm {

    private Long id;
    private String name;
    private int price;
    private int count;

    public Product toEntity() {
        return new Product(id, name ,price, count);
    }
}
