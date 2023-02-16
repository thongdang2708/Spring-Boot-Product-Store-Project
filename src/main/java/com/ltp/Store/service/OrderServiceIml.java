package com.ltp.Store.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ltp.Store.entity.Order;
import com.ltp.Store.entity.Product;
import com.ltp.Store.entity.User;
import com.ltp.Store.exception.OrderNotFoundWithIdException;
import com.ltp.Store.repository.OrderRepository;
import com.ltp.Store.repository.ProductRepository;

import java.util.List;

@Service
public class OrderServiceIml implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Override
    public Order getOrder(Long id) {
        Optional<Order> order = orderRepository.findById(id);

        if (order.isPresent()) {
            return order.get();
        } else {
            throw new OrderNotFoundWithIdException(id);
        }
    }

    @Override
    public Set<Product> getProductsByOrderId(Long id) {

        Order order = getOrder(id);

        return order.getProducts();
    }

    @Override
    public Order createOrder(Order order) {

        User user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        order.setUser(user);

        Order createdOrder = orderRepository.save(order);

        addProductsToOrder(createdOrder.getId(), order.getProductIds());

        return createdOrder;

    }

    public void addProductsToOrder(Long id, List<Integer> productIds) {

        Order order = getOrder(id);

        for (Integer productId : productIds) {
            Long castId = 0L;
            castId += productId;
            Product product = productService.getProduct(castId);
            order.addProduct(product);
            orderRepository.save(order);
        }
    }

    @Override
    public List<Order> getAllOrdersByUser() {
        User user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        return orderRepository.findByUserId(user.getId());
    }

    @Override
    public Order updateOrderIsPaid(Long id) {
        Order order = getOrder(id);

        order.changeIsPaid();
        order.changeDateAfterPayment();

        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {

        orderRepository.deleteById(id);
    }
}
