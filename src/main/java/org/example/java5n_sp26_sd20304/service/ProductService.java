package org.example.java5n_sp26_sd20304.service;

import lombok.RequiredArgsConstructor;
import org.example.java5n_sp26_sd20304.entity.Product;
import org.example.java5n_sp26_sd20304.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }
}
