package com.example.productservice.services.impl;

import com.example.productservice.dto.ProductDTO;
import com.example.productservice.entity.Products;
import com.example.productservice.repository.ProductsRepository;
import com.example.productservice.services.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductsServiceImpl implements ProductService {

    private final ProductsRepository productsRepository;

    public ProductsServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public String addProduct(ProductDTO productDTO,String token) {
        if(token.contains("ROLE_USER")) {
            throw new RuntimeException("You are not authorized to add a product");
        }
        Products product = Products.builder()
                .productName(productDTO.getProductName())
                .price(productDTO.getPrice())
                .quantity(productDTO.getQuantity())
                .build();
        productsRepository.save(product);
        return "Product added successfully";
    }

    public String updateProduct(ProductDTO productDTO,String token) {
        if(token.contains("ROLE_USER")) {
            throw new RuntimeException("You are not authorized to update a product");
        }
        Optional<Products> product = productsRepository.findByProductName(productDTO.getProductName());
        if (product.isPresent()) {
            product.get().setPrice(productDTO.getPrice());
            product.get().setQuantity(productDTO.getQuantity());
            productsRepository.save(product.get());
            return "Product updated successfully";
        }
        return "Product not found";
    }

    public String deleteProduct(String productName,String token) {
        if(token.contains("ROLE_USER")) {
            throw new RuntimeException("You are not authorized to delete a product");
        }
        Optional<Products> product = productsRepository.findByProductName(productName);
        if (product.isPresent()) {
            productsRepository.deleteByProductId(product.get().getProductId());
            return "Product deleted successfully";
        }
        return "Product not found";
    }

    public ProductDTO getProduct(String productName, String username) {
        String role = username;
        if (role.equals("ROLE_USER")){
        Optional<Products> product = productsRepository.findByProductName(productName);
        if (product.isPresent()) {
            return ProductDTO.builder()
                    .productName(product.get().getProductName())
                    .price(product.get().getPrice())
                    .quantity(product.get().getQuantity())
                    .build();
        }
    }else {
            throw new RuntimeException("You are not authorized to view this product");
        }
return null;
    }




    public List<ProductDTO> getAllProductsBySort(String sortType, String username) {
        List<Products> products = productsRepository.findAll();
        if (sortType.equals("asc")) {
            products.sort(Comparator.comparing(Products::getPrice));
        } else if (sortType.equals("desc")) {
            products.sort(Comparator.comparing(Products::getPrice).reversed());
        }
        return products.stream().map(product -> ProductDTO.builder()
                .productName(product.getProductName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build()).collect(Collectors.toList());
    }
}

