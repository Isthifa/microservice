package com.example.productservice.controller;

import com.example.productservice.dto.ProductDTO;
import com.example.productservice.entity.Products;
import com.example.productservice.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;
    private final RestTemplate restTemplate;



    public ProductController(ProductService productService, RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }

    @Operation(summary = "Add a new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product added successfully",
                    content = {@Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"productName\": \"Product1\", \"quantity\": 10, \"price\": 29.99}"))}),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Product already exists")
    })
    @PostMapping("/addProduct")
    public String addProduct(@RequestBody ProductDTO productDTO){
        return productService.addProduct(productDTO);
    }


    @Operation(summary = "Update a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully",
                    content = {@Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"productName\": \"Product1\", \"quantity\": 10, \"price\": 29.99}"))}),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @PutMapping("/updateProduct")
    public String updateProduct(@RequestBody ProductDTO productDTO){
        return productService.updateProduct(productDTO);
    }


    @Operation(summary = "Delete a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product deleted successfully",
                    content = {@Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"productName\": \"Product1\"" ))}),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @DeleteMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("productName") String productName,@RequestHeader("role") String token){
        return productService.deleteProduct(productName,token);
    }

    @Operation(summary = "Get all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products found"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Products not found")
    })
    @GetMapping("/getProduct")
    public ProductDTO getProduct(@RequestParam("productName") String productName){

        return productService.getProduct(productName);
    }

    @Operation(summary = "Get all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products found"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Products not found")
    })
    @GetMapping("/getAllProductsBySort")
    public List<ProductDTO> getAllProductsBySort(@RequestParam("sortType") String sortType,@RequestHeader("role") String username){
        return productService.getAllProductsBySort(sortType,username);
    }

    @Operation(summary = "update quantity of a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quantity updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @PutMapping("/updateQuantity/{productName}")
    public String updateQuantity(@PathVariable("productName") String productName,@RequestBody ProductDTO productDTO){
        return productService.updateQuantity(productName,productDTO);
    }



}
