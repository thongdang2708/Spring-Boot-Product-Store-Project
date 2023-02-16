package com.ltp.Store.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import java.util.ArrayList;
import java.util.List;
import com.ltp.Store.entity.Product;
import com.ltp.Store.objectstorage.ProductStorageObject;
import com.ltp.Store.service.ProductService;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductStorageObject> getProduct(@PathVariable Long id) {
        Product getProduct = productService.getProduct(id);
        ProductStorageObject productObject = new ProductStorageObject(getProduct.getId(), getProduct.getName(),
                getProduct.getYear(), getProduct.getPrice());
        return new ResponseEntity<>(productObject, HttpStatus.OK);
    }

    @PostMapping("/addProduct")
    public ResponseEntity<ProductStorageObject> addProductByUser(@Valid @RequestBody Product product) {
        Product createdProduct = productService.addProduct(product);
        ProductStorageObject productObject = new ProductStorageObject(createdProduct.getId(), createdProduct.getName(),
                createdProduct.getYear(), createdProduct.getPrice());
        return new ResponseEntity<>(productObject, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductStorageObject> updateProduct(@Valid @RequestBody Product product,
            @PathVariable Long id) {
        Product updatedProduct = productService.updateProduct(product, id);
        ProductStorageObject productObject = new ProductStorageObject(updatedProduct.getId(), updatedProduct.getName(),
                updatedProduct.getYear(), updatedProduct.getPrice());
        return new ResponseEntity<>(productObject, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/sort/{field}")
    public ResponseEntity<List<ProductStorageObject>> getProductsSortByField(@PathVariable String field) {

        List<Product> products = productService.getAllProductsWithSort(field);

        List<ProductStorageObject> productStorageObjects = new ArrayList<>();

        for (Product product : products) {
            ProductStorageObject productStorageObject = new ProductStorageObject(product.getId(), product.getName(),
                    product.getYear(), product.getPrice());
            productStorageObjects.add(productStorageObject);
        }

        return new ResponseEntity<>(productStorageObjects, HttpStatus.OK);
    }

    @GetMapping("/productsearch/{name}")
    public ResponseEntity<List<ProductStorageObject>> getProductsWithName(@PathVariable String name) {
        List<Product> products = productService.getProductsWithName(name);

        List<ProductStorageObject> productStorageObjects = new ArrayList<>();

        for (Product product : products) {
            ProductStorageObject productStorageObject = new ProductStorageObject(product.getId(), product.getName(),
                    product.getYear(), product.getPrice());
            productStorageObjects.add(productStorageObject);
        }

        return new ResponseEntity<>(productStorageObjects, HttpStatus.OK);
    }

    @GetMapping("/offset/{offset}/pageSize/{pageSize}")
    public ResponseEntity<List<ProductStorageObject>> getPagination(@PathVariable int offset,
            @PathVariable int pageSize) {
        Page<Product> listOfProducts = productService.getPaginationProduct(offset, pageSize);

        List<ProductStorageObject> productStorageObjects = new ArrayList<>();

        for (Product product : listOfProducts) {
            ProductStorageObject productStorageObject = new ProductStorageObject(product.getId(), product.getName(),
                    product.getYear(), product.getPrice());
            productStorageObjects.add(productStorageObject);
        }

        return new ResponseEntity<>(productStorageObjects, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductStorageObject>> getAllProducts() {
        List<Product> allProducts = productService.getAllProducts();

        List<ProductStorageObject> productStorageObject = new ArrayList<>();

        for (Product product : allProducts) {
            ProductStorageObject createdProductStorageObject = new ProductStorageObject(product.getId(),
                    product.getName(), product.getYear(), product.getPrice());
            productStorageObject.add(createdProductStorageObject);
        }
        return new ResponseEntity<>(productStorageObject, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Product>> getAllProductsCreatedByUser(@PathVariable Long userId) {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
