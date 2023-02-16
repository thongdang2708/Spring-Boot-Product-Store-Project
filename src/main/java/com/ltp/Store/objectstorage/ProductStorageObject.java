package com.ltp.Store.objectstorage;

public class ProductStorageObject {

    private Long id;
    private String name;
    private int year;
    private double price;

    public ProductStorageObject(Long id, String name, int year, double price) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.price = price;
    }

    public ProductStorageObject() {
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

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
