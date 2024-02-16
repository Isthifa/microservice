//package com.example.productservice.util;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class TokenValidator extends OncePerRequestFilter {
//
//    @Value("${order.service.url}")
//    private String orderServicePath; // Endpoint path of the Order Service
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        // Get the request path
//        String path = request.getRequestURI();
//
//        // Allow requests to the Order Service path without token validation
//        if (path.startsWith(orderServicePath)) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        // For all other paths, validate the token
//        String token = request.getHeader("Authorization");
//        if (token != null && token.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//        } else {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
//        }
//    }
//}