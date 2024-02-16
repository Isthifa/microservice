package com.example.orderservice.controller;

import com.example.orderservice.entity.OrderFile;
import com.example.orderservice.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Order already exists")
    })
    @PostMapping("/create")
    public RedirectView createOrder(@RequestParam("productName") String productName, @RequestParam("quantity") int quantity){
        int response = orderService.addOrder(productName, quantity);

        if(response==0){
            return new RedirectView("http://localhost:8082/product/getProduct?productName="+productName);
        }else {
            return new RedirectView("http://localhost:8083/order/confirm/"+response);
        }
    }

    @Operation(summary = "Confirm a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order confirmed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @GetMapping("/confirm/{orderId}")
    public String confirm(@PathVariable int orderId){
        return "http://localhost:8083/order/confirm/"+orderId;
    }
    @PostMapping("/confirm/{orderId}")
    public String confirmOrder(@PathVariable int orderId){
        return orderService.confirmOrder(orderId);
    }

    @Operation(summary = "Get an order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order found successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @GetMapping("/getOrder/{orderId}")
    public String getOrder(@PathVariable int orderId){
        return orderService.getOrder(orderId).toString();
    }

    @Operation(summary = "Get all orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders found successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Orders not found")
    })

    @GetMapping("/getAllOrders")
    public List<OrderFile> getAllOrders(){
        return orderService.getAllOrders();
    }


}
