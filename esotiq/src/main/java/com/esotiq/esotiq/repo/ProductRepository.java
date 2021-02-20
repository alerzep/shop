package com.esotiq.esotiq.repo;

import com.esotiq.esotiq.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT model FROM product", nativeQuery = true)
    List<String> getAllModels();
}
