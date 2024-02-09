package com.example.productservice.repository;

import com.example.productservice.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductsRepository extends JpaRepository<Products, UUID>
{
    Optional<Products> findByProductId(UUID productId);
    Optional<Products> findByProductName(String productName);
    void deleteByProductId(UUID productId);

    List<Products> findAll();
}
