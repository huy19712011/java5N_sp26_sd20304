package org.example.java5n_sp26_sd20304.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java5n_sp26_sd20304.entity.Product;
import org.example.java5n_sp26_sd20304.service.CategoryService;
import org.example.java5n_sp26_sd20304.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/products")
    public String listProducts(Model model) {

        /*
        // get data from db
        List<Product> products = productService.getAllProducts();

        // send data to view
        model.addAttribute("products", products);

        return "views/products"; // .html
        */

        return findPaginated(1, "name", "asc", model);
    }

    @GetMapping("/products/showNewProductForm")
    public String showNewProductForm(Model model) {

        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());

        return "views/new_product";
    }

    @PostMapping("/products/saveProduct")
    public String saveProduct(@Valid @ModelAttribute("product") Product product,
                              BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {

            return "views/new_product";
        }

        productService.saveProduct(product);

        return "redirect:/products";
    }

    @GetMapping("/products/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable("id") long id, Model model) {

        Product product = productService.getProductById(id);

        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());

        return "views/update_product";
    }

    @PostMapping("/products/updateProduct")
    public String updateProduct(@Valid @ModelAttribute("product") Product product,
                                BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("categories", categoryService.getAllCategories());

            return "views/update_product";
        }

        productService.updateProduct(product);

        return "redirect:/products";
    }

    @GetMapping("/products/page/{pageNo}")
    public String findPaginated(@PathVariable int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {

        int pageSide = 1;

        Page<Product> page = productService.findPaginated(pageNo, pageSide, sortField, sortDir);

        List<Product> products = page.getContent();

        // send data to view
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("pageSize", pageSide);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("products", products);

        // view
        return "views/products";
    }

}
