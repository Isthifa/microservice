package com.example.productservice.controller;

import com.example.productservice.dto.ProductDTO;
import com.example.productservice.entity.Products;
import com.example.productservice.services.ProductService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final RestTemplate restTemplate;



    public ProductController(ProductService productService, RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestBody ProductDTO productDTO,@RequestHeader("role") String token){
        return productService.addProduct(productDTO,token);
    }


    @PutMapping("/updateProduct")
    public String updateProduct(@RequestBody ProductDTO productDTO,@RequestHeader("role") String token){
        return productService.updateProduct(productDTO,token);
    }


    @DeleteMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("productName") String productName,@RequestHeader("role") String token){
        return productService.deleteProduct(productName,token);
    }

    @GetMapping("/getProduct")
    public ProductDTO getProduct(@RequestParam("productName") String productName,@RequestHeader("role") String username){
//        String name=restTemplate.getForObject("http://localhost:8081/user/extractUsername?token="+token,String.class); //This is the line that needs to be changed to get the username from the token
        return productService.getProduct(productName,username);
    }

    @GetMapping("/getAllProductsBySort")
    public List<ProductDTO> getAllProductsBySort(@RequestParam("sortType") String sortType,@RequestHeader("role") String username){
        return productService.getAllProductsBySort(sortType,username);
    }



}
