package com.ltp.Store.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.ltp.Store.entity.Order;
import com.ltp.Store.entity.Product;
import com.ltp.Store.objectstorage.OrderStorageObject;
import com.ltp.Store.objectstorage.ProductStorageObject;
import com.ltp.Store.objectstorage.UserStorageObject;
import com.ltp.Store.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderStorageObject> getSingleOrder(@PathVariable Long id) {
        Order order = orderService.getOrder(id);
        Set<Product> products = orderService.getProductsByOrderId(id);
        List<ProductStorageObject> productStorageObjects = new ArrayList<>();

        for (Product product : products) {
            ProductStorageObject productStorageObject = new ProductStorageObject(product.getId(), product.getName(),
                    product.getYear(), product.getPrice());
            productStorageObjects.add(productStorageObject);
        }

        OrderStorageObject orderStorageObject = new OrderStorageObject(order.getId(), order.getOrderDate(),
                order.getPaymentDate(), order.getIsPaid(), order.getUser().getId(), productStorageObjects);

        return new ResponseEntity<>(orderStorageObject, HttpStatus.OK);
    }

    @PostMapping("/createOrder")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/orderByUser")
    public ResponseEntity<List<OrderStorageObject>> getAllOrdersByUser() {
        List<Order> orders = orderService.getAllOrdersByUser();
        List<OrderStorageObject> orderStorageObjects = new ArrayList<>();
        for (Order order : orders) {
            OrderStorageObject eachOrderStorageObject = new OrderStorageObject(order.getId(), order.getOrderDate(),
                    order.getPaymentDate(), order.getIsPaid(), order.getUser().getId(),
                    new UserStorageObject(order.getUser().getId(), order.getUser().getUsername()));
            orderStorageObjects.add(eachOrderStorageObject);
        }
        return new ResponseEntity<>(orderStorageObjects, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderStorageObject> updateOrderWhenIsPaid(@PathVariable Long id) {

        Order order = orderService.updateOrderIsPaid(id);

        OrderStorageObject orderStorageObject = new OrderStorageObject(order.getId(), order.getOrderDate(),
                order.getPaymentDate(), order.getIsPaid(), order.getUser().getId());
        return new ResponseEntity<>(orderStorageObject, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
