package com.example.minimini.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity // 해당 클래스로 table을 만드는 것
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id를 자동 생성해주는 어노테이션
    private Long id;

    @Column // 테이블 안의 컬럼들을 지정한 것임
    private String name;

    @Column
    private Integer price;

    @Column
    private Integer stock;

    public void patch(Product product) {
        if (product.name != null)
            this.name = product.name;

        if (product.price != null)
            this.price = product.price;

        if (product.stock != null)
            this.stock = product.stock;
    }
}
