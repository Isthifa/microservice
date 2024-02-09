package com.example.orderservice.services;

public interface OrderService {
    public String addOrder(String productName, int quantity, String token);
    public String updateOrder(String productName, int quantity, String token);
    public String deleteOrder(String productName, String token);
    public String confirmOrder(String productName, String token);
    public String cancelOrder(String productName, String token);
    public String getOrder(String productName, String token);
    public String getAllOrders(String token);
    public String getAllOrdersBySort(String sortType, String token);
}
