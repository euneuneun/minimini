package com.example.minimini.service;

import com.example.minimini.dto.ProductForm;
import com.example.minimini.entity.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 해당 클래스는 스프링부트와 연동되어 테스팅된다.
class ProductServiceTest {

    @Autowired ProductService productService;

    @Test
    void index() {
        // 예상
        Product a = new Product(1L,"초코송이", 1000, 3);
        Product b = new Product(2L,"웨하스", 1000, 5);
        Product c = new Product(3L,"버터와플", 1500, 3);
        List<Product> expected = new ArrayList<Product>(Arrays.asList(a,b,c));
        // 실제
        List<Product> products =  productService.index();
        // 비교
        assertEquals(expected.toString(), products.toString());
    }

    @Test
    void show_성공____존재하는_id_입력() {
        //예상
        Long id = 1L;
        Product expected = new Product(id, "초코송이", 1000, 3);
        //실제
        Product product = productService.show(id);
        //비교
        assertEquals(expected.toString(), product.toString());

    }

    @Test
    void show_실패____존재하지_않는_id_입력() {
        //예상
        Long id = -1L;
        Product expected = null;
        //실제
        Product product = productService.show(id);
        //비교
        assertEquals(expected, product);
    }

    @Test
    @Transactional
    void create_성공____name과_price__count만_있는_dto_입력() {
        //예상
        String title = "빵";
        int price = 500;
        int count = 5;
        ProductForm dto = new ProductForm(null, title, price, count);
        Product expected = new Product(4L, title, price, count);
        //실제
        Product product = productService.create(dto);
        //비교
        assertEquals(expected.toString(), product.toString());
    }

    @Test
    @Transactional
    void create_실패____id가_포함된_dto_입력() {
        //예상
        String title = "빵";
        int price = 500;
        int count = 5;
        ProductForm dto = new ProductForm(4L, title, price, count);
        Product expected = null;
        //실제
        Product product = productService.create(dto);
        //비교
        assertEquals(expected, product);
    }


}