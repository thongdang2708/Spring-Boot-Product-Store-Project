package com.ltp.Store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ltp.Store.entity.Product;
import java.util.List;

public interface ProductJPARepository extends JpaRepository<Product, Long> {

    @Modifying
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    List<Product> searchProductsWithName(@Param("name") String name);
}
