//package com.example.productservice.util;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class TokenValidator extends OncePerRequestFilter {
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//         String token=request.getHeader("Authorization");
//         if(token.startsWith("Bearer ")){
//             filterChain.doFilter(request,response);
//         }
//         else{
//             response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid Token");
//         }
//    }
//}
