package com.esotiq.esotiq.repo;

import com.esotiq.esotiq.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
