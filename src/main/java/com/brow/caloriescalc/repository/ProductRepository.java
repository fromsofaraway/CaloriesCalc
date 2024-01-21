package com.brow.caloriescalc.repository;

import com.brow.caloriescalc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
