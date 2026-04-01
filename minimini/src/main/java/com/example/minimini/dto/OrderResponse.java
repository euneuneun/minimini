// minimini/src/main/java/com/example/minimini/dto/OrderResponse.java
package com.example.minimini.dto;

import com.example.minimini.entity.Order;
import lombok.Getter;

@Getter
public class OrderResponse {
    private Long id;
    private Long productId;
    private String productName; // 상품 이름을 여기서 꺼내서 담음
    private int quantity;

    public OrderResponse(Order order) {
        this.id = order.getId();
        this.productId = order.getProduct().getId();
        this.productName = order.getProduct().getName(); // 항상 최신 이름
        this.quantity = order.getQuantity();
    }
}