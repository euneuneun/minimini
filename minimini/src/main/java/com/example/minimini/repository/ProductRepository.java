package com.example.minimini.repository;

import com.example.minimini.entity.Product;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product,Long> {
    @Override
    ArrayList<Product> findAll();

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name="jakarta.persistence.lock.timeout",value="3000")})
    @Query("SELECT p FROM Product p WHERE p.id= :id")
    Optional<Product> findByWithLock(@Param("id") Long id);
}
