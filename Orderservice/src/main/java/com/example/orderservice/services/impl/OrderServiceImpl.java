package com.example.orderservice.services.impl;

import com.example.orderservice.services.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public String addOrder(String productName, int quantity, String token) {
        return null;
    }

    @Override
    public String updateOrder(String productName, int quantity, String token) {
        return null;
    }

    @Override
    public String deleteOrder(String productName, String token) {
        return null;
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
