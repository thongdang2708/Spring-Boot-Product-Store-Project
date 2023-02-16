package com.ltp.Store.service;

import java.util.Set;
import java.util.List;

import com.ltp.Store.entity.Order;
import com.ltp.Store.entity.Product;

public interface OrderService {
    Order getOrder(Long id);

    Order createOrder(Order order);

    Set<Product> getProductsByOrderId(Long id);

    List<Order> getAllOrdersByUser();

    Order updateOrderIsPaid(Long id);

    void deleteOrder(Long id);
}
