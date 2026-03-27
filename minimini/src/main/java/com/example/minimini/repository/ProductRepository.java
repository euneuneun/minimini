package com.example.minimini.repository;

import com.example.minimini.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ProductRepository extends CrudRepository<Product,Long> {
    @Override
    ArrayList<Product> findAll();
}
