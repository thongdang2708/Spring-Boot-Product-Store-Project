package com.ltp.Store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ltp.Store.entity.Product;
import com.ltp.Store.entity.User;
import com.ltp.Store.exception.ProductNotFoundWithIdException;
import com.ltp.Store.repository.ProductJPARepository;
import com.ltp.Store.repository.ProductRepository;
import com.ltp.Store.repository.ProductWithSortingAndPagingRepository;
import com.ltp.Store.repository.UserRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@Service
public class ProductServiceIml implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductWithSortingAndPagingRepository productWithSortingAndPagingRepository;

    @Autowired
    private ProductJPARepository productJPARepository;

    @Override
    public Product getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            return product.get();
        } else {
            throw new ProductNotFoundWithIdException(id);
        }
    }

    @Override
    public Product addProduct(Product product) {

        User user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        product.setUser(user);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product, Long productId) {

        User user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        Optional<Product> updatedProduct = productRepository.findByIdAndUserId(productId, user.getId());

        if (updatedProduct.isPresent()) {
            Product unwrappedProduct = updatedProduct.get();
            unwrappedProduct.setName(product.getName());
            unwrappedProduct.setPrice(product.getPrice());
            unwrappedProduct.setYear(product.getYear());
            return productRepository.save(unwrappedProduct);
        } else {
            throw new ProductNotFoundWithIdException(productId);
        }

    }

    @Override
    public void deleteProduct(Long productId) {

        User user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        productRepository.deleteByIdAndUserId(productId, user.getId());

    }

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsWithSort(String field) {

        return productWithSortingAndPagingRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    @Override
    public Page<Product> getPaginationProduct(int offset, int pageSize) {
        // TODO Auto-generated method stub
        return productWithSortingAndPagingRepository.findAll(PageRequest.of(offset, pageSize));
    }

    @Override
    public List<Product> getProductsWithName(String name) {

        return productJPARepository.searchProductsWithName(name.toLowerCase());

    }

}
