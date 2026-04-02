package com.example.minimini.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderForm {
    private Long productId; // 주문할 상품의 id
    private int quantity; // 주문 수량
}
