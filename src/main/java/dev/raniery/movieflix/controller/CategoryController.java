package dev.raniery.movieflix.controller;

import dev.raniery.movieflix.entity.Category;
import dev.raniery.movieflix.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movieflix/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    @PostMapping
    public Category saveCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @GetMapping("/{id}")
    public Category getByCategoryId(@PathVariable Long id) {
        return categoryService.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteByCategoryId(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
