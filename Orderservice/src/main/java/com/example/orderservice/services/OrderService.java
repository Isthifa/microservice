package com.example.orderservice.services;

import com.example.orderservice.entity.OrderFile;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    public int addOrder(String productName, int quantity);

    public String confirmOrder(int orderId);
    public String cancelOrder(String productName);
    public OrderFile getOrder(int orderId);
    public List<OrderFile> getAllOrders();

}
