package com.ltp.Store.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ltp.Store.entity.Order;

import jakarta.transaction.Transactional;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByUserId(Long userId);

    @Transactional
    void deleteByIdAndUserId(Long orderId, Long userId);
}
