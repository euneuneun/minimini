package com.example.minimini.api;

import com.example.minimini.dto.ProductForm;
import com.example.minimini.entity.Product;
import com.example.minimini.repository.ProductRepository;
import com.example.minimini.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ProductApiController {

    @Autowired
    private ProductService productService;

    //GET (전체조회)
    @GetMapping("/api/products")
    public List<Product> index(){
        return productService.index();
    }

    //GET (단일조회)
    @GetMapping("/api/products/{id}")
    public Product show(@PathVariable Long id){
        return productService.show(id);
    }

    //POST (생성)
    @PostMapping("/api/products")
    public ResponseEntity<Product> create(@RequestBody ProductForm dto){
        Product created= productService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //PATCH (수정)
    @PatchMapping("/api/products/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody ProductForm dto){

        Product updated = productService.updated(id,dto);

        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //DELETE(삭제)
    @DeleteMapping("/api/products/{id}")
    public ResponseEntity<Product> delete(@PathVariable Long id){

        Product deleted = productService.delete(id);
        // 데이터 반환
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 트랜잭션 -> 실패 -> 롤백!
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Product>> transactionTest(@RequestBody List<ProductForm> dtos){
        List<Product> createdList = productService.createProducts(dtos);
        return (createdList!=null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
