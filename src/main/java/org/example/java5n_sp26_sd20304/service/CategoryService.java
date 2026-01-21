package org.example.java5n_sp26_sd20304.service;

import lombok.RequiredArgsConstructor;
import org.example.java5n_sp26_sd20304.entity.Category;
import org.example.java5n_sp26_sd20304.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {

        return categoryRepository.findAll();
    }
}
