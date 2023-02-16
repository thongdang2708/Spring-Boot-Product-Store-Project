package com.ltp.Store.repository;

import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ltp.Store.entity.Product;

import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<Product> findByIdAndUserId(Long productId, Long userId);

    @Transactional
    void deleteByIdAndUserId(Long productId, Long userId);

}
