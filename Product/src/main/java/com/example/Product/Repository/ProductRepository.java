package com.example.Product.Repository;

import com.example.Product.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product,Integer> {
}
