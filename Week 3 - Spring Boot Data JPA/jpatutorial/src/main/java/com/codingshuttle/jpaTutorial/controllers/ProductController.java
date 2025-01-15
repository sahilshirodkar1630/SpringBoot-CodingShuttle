package com.codingshuttle.jpaTutorial.controllers;

import com.codingshuttle.jpaTutorial.entities.Product;
import com.codingshuttle.jpaTutorial.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final int PAGE_SIZE = 5;

    private final ProductRepository productRepository;
    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getAllProducts(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "id") String sortBy
    ){
        //return productRepository.findBy(Sort.by(Sort.Direction.DESC,sortBy,"price","quantity"));
        //return productRepository.findBy(Sort.by(
       //         Sort.Order.desc(sortBy),
          //      Sort.Order.asc("title")
        //));
        Pageable pageable = PageRequest.of(pageNumber,PAGE_SIZE);
        return productRepository.findAll(pageable).getContent();

    }

    @GetMapping("/titleContaining")
    public List<Product> getAllProductByTitleContaining(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "id") String sortBy
    ){

        return productRepository.findByTitleContainingIgnoreCase(title,PageRequest.of(pageNumber,PAGE_SIZE)).getContent();

    }
}
