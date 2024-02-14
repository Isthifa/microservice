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

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class OrderServiceImpl implements OrderService {
    RestClient restClient = RestClient.create("http://localhost:8082/product/");

    @Autowired
    private OrderRepository orderRepository;




    @Override
    public int addOrder(String productName, int quantity) {
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

            return orderFile.getOrderId();
        }else {
            return 0;
        }

    }




    @Override
    public String confirmOrder(int orderId) {
       Optional<OrderFile> orderFile= orderRepository.findByOrderId(orderId);
         if (orderFile.isPresent()){
             OrderFile orderFile1=orderFile.get();
                orderFile1.setIsOrderConfirmed("true");
                orderRepository.save(orderFile1);
            }
         return "Order confirmed successfully";
    }

    @Override
    public String cancelOrder(String productName) {
        //TODO: Implement this method
        //when you cancel the order, the quantity of the product should be updated
        //to cancel the order, you need update confirmOrder to false and update the quantity of the product
        return null;
    }


    @Override
    public OrderFile getOrder(int orderId) {
        return orderRepository.findByOrderId(orderId).get();
    }

    @Override
    public List<OrderFile> getAllOrders() {
        return orderRepository.findAll();
    }


}
