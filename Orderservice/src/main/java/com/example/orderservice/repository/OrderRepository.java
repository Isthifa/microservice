package com.example.orderservice.repository;

import com.example.orderservice.entity.OrderFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderFile, UUID> {


    Optional<OrderFile> findByOrderId(int orderId);
}
