package com.example.minimini.service;

import com.example.minimini.dto.ProductForm;
import com.example.minimini.entity.Product;
import com.example.minimini.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service // 서비스 선언( (서비스 객체를 스프링부트에 선언)
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> index() {
        return productRepository.findAll();
    }

    public Product show(Long id) {
        return productRepository.findById(id).orElse(null);
    }


    public Product create(ProductForm dto) {
        Product product = dto.toEntity();
        if (product.getId() != null)
            return null;
        return productRepository.save(product);
    }

    public Product updated(Long id, ProductForm dto) {
        // 1. 수정용 엔티티 생성
        Product product = dto.toEntity();
        log.info("id: {}, product: {}", id, product.toString());

        // 2. 대상 엔티티 찾기
        Product target = productRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리(대상이 없거나, id가 다른 경우)
        if (target == null || id != product.getId()) {
            log.info("잘못된 요청! id : {}, product: {}", id, product.toString());
            return null;
        }

        // 4. 업데이트
        target.patch(product);
        Product updated = productRepository.save(target);
        return updated;
    }


    public Product delete(Long id) {
        // 대상 찾기
        Product target = productRepository.findById(id).orElse(null);
        // 잘못된 요청 처리
        if (target == null){ return null;}
        // 대상 삭제
        productRepository.delete(target);

        return target;
    }

    @Transactional // 해당 메소드를 트랜잭션으로 묶는다.
    public List<Product> createProducts(List<ProductForm> dtos) {
        //dto 묶음을 entity 묶음으로 변환
        List<Product> productList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());

        // entity 묶음을 DB로 저장
        productList.stream()
                .forEach(product -> productRepository.save(product));

        // 강제 예외 발생
        productRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("결제 실패")
        );

        // 결과값 반환;
        return productList;
    }


}