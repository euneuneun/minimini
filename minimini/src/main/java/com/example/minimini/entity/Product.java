package com.example.minimini.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity // DB가 해당 객체 인식 가능!
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private int price;

    @Column
    private int count;

}
