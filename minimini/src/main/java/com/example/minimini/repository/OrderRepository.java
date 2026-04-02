package com.example.minimini.repository;

import com.example.minimini.entity.Order;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT o FROM Order o JOIN FETCH o.product",
            countQuery = "SELECT count(o) FROM Order o")
    Page<Order> findAllWithProduct(Pageable pageable);
}
