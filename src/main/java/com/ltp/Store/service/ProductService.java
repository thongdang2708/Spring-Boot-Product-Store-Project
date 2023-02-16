package com.ltp.Store.service;

import com.ltp.Store.entity.Product;
import java.util.List;

import org.springframework.data.domain.Page;

public interface ProductService {
    Product getProduct(Long id);

    Product addProduct(Product product);

    Product updateProduct(Product product, Long productId);

    void deleteProduct(Long productId);

    List<Product> getAllProducts();

    List<Product> getAllProductsWithSort(String field);

    Page<Product> getPaginationProduct(int offset, int pageSize);

    List<Product> getProductsWithName(String name);
}
