package com.ltp.Store.objectstorage;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ltp.Store.entity.Product;

import java.util.List;

public class OrderStorageObject {

    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate orderDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;

    private Boolean isPaid;

    private Long userId;

    private List<ProductStorageObject> products;

    private UserStorageObject userStorageObject;

    public OrderStorageObject(Long id, LocalDate orderDate, LocalDate paymentDate, Boolean isPaid, Long userId,
            List<ProductStorageObject> products) {
        this.id = id;
        this.orderDate = orderDate;
        this.paymentDate = paymentDate;
        this.isPaid = isPaid;
        this.userId = userId;
        this.products = products;
    }

    public OrderStorageObject(Long id, LocalDate orderDate, LocalDate paymentDate, Boolean isPaid, Long userId,
            UserStorageObject userStorageObject) {
        this.id = id;
        this.orderDate = orderDate;
        this.paymentDate = paymentDate;
        this.isPaid = isPaid;
        this.userId = userId;
        this.userStorageObject = userStorageObject;
    }

    public OrderStorageObject(Long id, LocalDate orderDate, LocalDate paymentDate, Boolean isPaid, Long userId) {
        this.id = id;
        this.orderDate = orderDate;
        this.paymentDate = paymentDate;
        this.isPaid = isPaid;
        this.userId = userId;
    }

    public OrderStorageObject() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getPaymentDate() {
        return this.paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Boolean isIsPaid() {
        return this.isPaid;
    }

    public Boolean getIsPaid() {
        return this.isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<ProductStorageObject> getProducts() {
        return this.products;
    }

    public void setProducts(List<ProductStorageObject> products) {
        this.products = products;
    }

    public UserStorageObject getUserStorageObject() {
        return this.userStorageObject;
    }

    public void setUserStorageObject(UserStorageObject userStorageObject) {
        this.userStorageObject = userStorageObject;
    }

}
