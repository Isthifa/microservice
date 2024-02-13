package com.example.orderservice.services.impl;

import com.example.orderservice.dto.ProductDTO;
import com.example.orderservice.entity.OrderFile;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import reactor.core.publisher.Mono;


@Service
public class OrderServiceImpl implements OrderService {
    RestClient restClient = RestClient.create("http://localhost:8082/product/");

    @Autowired
    private OrderRepository orderRepository;




    @Override
    public String addOrder(String productName, int quantity) {
//        RestClient restClient1 = RestClient.create();
        ProductDTO productDTO=restClient.get().uri("/getProduct?productName="+productName)
                .retrieve()
                .body(ProductDTO.class);
        if (productDTO.getQuantity() >= quantity) {
            ProductDTO updatedProductDTO = ProductDTO.builder()
                    .productName(productDTO.getProductName())
                    .quantity(productDTO.getQuantity() - quantity)
                    .price(productDTO.getPrice())
                    .build();

            restClient.put().uri("/updateQuantity/{productName}", productName)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(updatedProductDTO)
                    .retrieve();

            OrderFile orderFile = OrderFile.builder()
                    .productName(productName)
                    .quantity(quantity)
                    .build();
            orderRepository.save(orderFile);

            return "Order placed successfully";
        }else {
            return "Product not available";
        }

    }

    @Override
    public String updateOrder(String productName, int quantity, String token) {
       return null;
    }

    @Override
    public String deleteOrder(String productName, String token) {
        return
    }

    @Override
    public String confirmOrder(String productName, String token) {
        return null;
    }

    @Override
    public String cancelOrder(String productName, String token) {
        return null;
    }

    @Override
    public String getOrder(String productName, String token) {
        return null;
    }

    @Override
    public String getAllOrders(String token) {
        return null;
    }

    @Override
    public String getAllOrdersBySort(String sortType, String token) {
        return null;
    }
}
