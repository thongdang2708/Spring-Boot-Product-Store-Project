package com.ltp.Store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ltp.Store.entity.Product;
import java.util.List;

public interface ProductWithSortingAndPagingRepository extends PagingAndSortingRepository<Product, Long> {

    List<Product> findAll(Sort sort);

}
