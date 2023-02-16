package com.ltp.Store.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ltp.Store.validator.ProductValidator.CheckPrice;
import com.ltp.Store.validator.ProductValidator.CheckYear;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Product name must not be blank!")
    @Column(name = "name", nullable = false)
    private String name;

    @CheckYear(message = "Year must be this year or in the past!")
    @Column(name = "year", nullable = false)
    private Integer year;

    @CheckPrice(message = "Price must be larger than 0")
    @Column(name = "price", nullable = false)
    private Double price;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "order_product", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "order_id"))
    private Set<Order> orders = new HashSet<>();
    // @JsonIgnore
    // @ManyToMany
    // @JoinTable(name = "order_product", joinColumns = @JoinColumn(name =
    // "product_id", referencedColumnName = "id"), inverseJoinColumns =
    // @JoinColumn(name = "order_id"))
    // private Set<Order> orders = new HashSet<>();

    public Product(Long id, String name, Integer year, Double price) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.price = price;
    }

    public Product() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

}
