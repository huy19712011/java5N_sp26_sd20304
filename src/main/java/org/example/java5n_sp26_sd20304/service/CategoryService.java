package org.example.java5n_sp26_sd20304.service;

import lombok.RequiredArgsConstructor;
import org.example.java5n_sp26_sd20304.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
}
