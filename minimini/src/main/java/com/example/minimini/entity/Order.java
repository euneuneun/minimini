package com.example.minimini.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders") // order는 예약어라 테이블명 충돌 방지
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 해당 주문 엔티티 여러개가, 하나의 Product에 연관된다.
    @JoinColumn(name="product_id")
    private Product product;

    @Column
    private int quantity;

}
