package com.example.productservice.services;


import com.example.productservice.dto.ProductDTO;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

public interface ProductService {

    String addProduct(ProductDTO productDTO,String username);
    String updateProduct(ProductDTO productDTO);
    String deleteProduct(String productName,String username);
    ProductDTO getProduct(String productName);
    List<ProductDTO> getAllProductsBySort(String sortType,String username);

    String updateQuantity(String productName,ProductDTO productDTO);

}
