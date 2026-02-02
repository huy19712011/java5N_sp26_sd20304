package org.example.java5n_sp26_sd20304.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java5n_sp26_sd20304.entity.Product;
import org.example.java5n_sp26_sd20304.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    public void saveProduct(@Valid Product product) {

        productRepository.save(product);
    }

    public Product getProductById(long id) {

        return productRepository.findById(id).get();
    }

    public void updateProduct(@Valid Product product) {

        productRepository.save(product);
    }

    public Page<Product> findPaginated(int pageNo, int pageSide, String sortField, String sortDir) {

        return productRepository.findAll(PageRequest.of(pageNo -1, pageSide,
                Sort.by(Sort.Direction.fromString(sortDir), sortField)));
    }
}
