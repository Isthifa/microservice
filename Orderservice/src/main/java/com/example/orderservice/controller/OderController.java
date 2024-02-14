package com.example.orderservice.controller;

import com.example.orderservice.entity.OrderFile;
import com.example.orderservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public RedirectView createOrder(@RequestParam("productName") String productName, @RequestParam("quantity") int quantity){
        int response = orderService.addOrder(productName, quantity);

        if(response==0){
            return new RedirectView("http://localhost:8082/product/getProduct?productName="+productName);
        }else {
            return new RedirectView("http://localhost:8083/order/confirm/"+response);
        }
    }

    @GetMapping("/confirm/{orderId}")
    public String confirm(@PathVariable int orderId){
        return "http://localhost:8083/order/confirm/"+orderId;
    }
    @PostMapping("/confirm/{orderId}")
    public String confirmOrder(@PathVariable int orderId){
        return orderService.confirmOrder(orderId);
    }

    @GetMapping("/getOrder/{orderId}")
    public String getOrder(@PathVariable int orderId){
        return orderService.getOrder(orderId).toString();
    }

    @GetMapping("/getAllOrders")
    public List<OrderFile> getAllOrders(){
        return orderService.getAllOrders();
    }


}
