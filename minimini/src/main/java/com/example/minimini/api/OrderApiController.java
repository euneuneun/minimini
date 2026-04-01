// minimini/src/main/java/com/example/minimini/api/OrderApiController.java
package com.example.minimini.api;

import com.example.minimini.dto.OrderForm;
import com.example.minimini.dto.OrderResponse;
import com.example.minimini.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class OrderApiController {

    @Autowired
    private OrderService orderService;

    // 주문 생성
    @PostMapping("/api/orders")
    public ResponseEntity<OrderResponse> create(@RequestBody OrderForm form) {
        OrderResponse created = orderService.create(form);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 주문 단건 조회
    @GetMapping("/api/orders/{id}")
    public ResponseEntity<OrderResponse> show(@PathVariable Long id) {
        OrderResponse order = orderService.show(id);
        return (order != null) ?
                ResponseEntity.status(HttpStatus.OK).body(order) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}