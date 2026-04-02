// minimini/src/main/java/com/example/minimini/service/OrderService.java
package com.example.minimini.service;

import com.example.minimini.dto.OrderForm;
import com.example.minimini.dto.OrderResponse;
import com.example.minimini.entity.Order;
import com.example.minimini.entity.Product;
import com.example.minimini.repository.OrderRepository;
import com.example.minimini.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;


@Slf4j
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    // 주문 생성
    @Transactional
    public OrderResponse create(OrderForm form) {
        log.info("주문 생성 요청: {}",form);

        // 1. productId로 실제 Product 찾기
        Product product = productRepository.findById(form.getProductId()).orElse(null);

        // 2. 상품이 없으면 null 반환
        if (product == null) {
            log.info("상품을 찾을 수 없습니다. productId: {}", form.getProductId());
            return null;
        }

        // 3. Order 엔티티 생성 후 저장
        Order order = new Order(null, product, form.getQuantity());
        Order saved = orderRepository.save(order);
        log.info("주문 생성 완료: {}", saved);

        // 4. 응답 DTO로 변환해서 반환
        return new OrderResponse(saved);
    }

    // 주문 단건 조회
    public OrderResponse show(Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) return null;
        return new OrderResponse(order);
    }


    public Page<OrderResponse> getOrders(Pageable pageable) {
    // 1. DB에서 페이지 단위로 order 조회
        Page<Order> orderPage = orderRepository.findAll(pageable);
        return orderPage.map(OrderResponse::new);
    }

}