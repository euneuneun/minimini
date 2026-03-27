package com.example.minimini.controller;

import com.example.minimini.dto.ProductForm;
import com.example.minimini.entity.Product;
import com.example.minimini.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("products/new") // 새 상품 등록 form
    public String newProductForm(){
        return "products/new";
    }

    @PostMapping("products/create") // DB에 상품 등록 후 방금 등록한 상품 페이지 보여줌
    public String createaProduct(ProductForm form){
        log.info(form.toString());

        // 1. DTO -> Entity
        Product product = form.toEntity();
        log.info(product.toString());

        // 2. Repository에게 Entity를 DB안에 저장하게 함.
        Product saved = productRepository.save(product);
        log.info(saved.toString());

        return "redirect:/products/" + saved.getId();
    }

    @GetMapping("/products/{id}") // id와 일치하는 페이지 보여줌
    public String detail(@PathVariable Long id, Model model) {
        log.info("id = " + id);

        // 1 : id로 데이터(Entity)를 가져옴!
        Product productEntity = productRepository.findById(id).orElse(null);

        // 2 : 가져온 데이터를 모델에 등록!
        model.addAttribute("product",productEntity);

        // 3 : 보여줄 페이지를 설정!
        return "/products/detail";
    }

    @GetMapping("/products") // 등록한 상품 list 페이지 보여줌
    public String list(Model model){

        List<Product> productEntityList = productRepository.findAll();

        model.addAttribute("productList",productEntityList);

        return "/products/list";
    }

    @GetMapping("/products/{id}/edit") // 수정 페이지
    public String edit(@PathVariable Long id, Model model){

        Product productEntity = productRepository.findById(id).orElse(null);

        model.addAttribute("product",productEntity);

        return "/products/edit";
    }

    @PostMapping("/products/update")// 수정 데이터 DB저장
    public String updatet(ProductForm form, Model model){
        log.info(form.toString());

        Product productEntity = form.toEntity();
        log.info(form.toString());

        Product target = productRepository.findById(productEntity.getId()).orElse(null);
        if (target!=null){
            productRepository.save(productEntity);
        }

        return "redirect:/products/" + productEntity.getId();
    }

    @GetMapping("/products/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제요청이 들어왔습니다!");

        // 1 : 삭제 대상 가져오기
        Product target = productRepository.findById(id).orElse(null);
        // 2: 대상 삭제
        if(target != null){
            productRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제가 완료되었습니다!");
        }
        // 3: 결과 페이지로 리다이렉트
        return "redirect:/products";
    }


}
