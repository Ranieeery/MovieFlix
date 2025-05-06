package dev.raniery.movieflix.controller;

import dev.raniery.movieflix.controller.request.CategoryRequest;
import dev.raniery.movieflix.controller.response.CategoryResponse;
import dev.raniery.movieflix.entity.Category;
import dev.raniery.movieflix.mapper.CategoryMapper;
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
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        return categories
            .stream()
            .map(CategoryMapper::toCategoryResponse)
            .toList();
    }

    @PostMapping
    public CategoryResponse saveCategory(@RequestBody CategoryRequest request) {
        Category category = CategoryMapper.toCategory(request);
        Category sadevCategory = categoryService.saveCategory(category);

        return CategoryMapper.toCategoryResponse(sadevCategory);
    }

    @GetMapping("/{id}")
    public CategoryResponse getByCategoryId(@PathVariable Long id) {
        return categoryService
            .findById(id)
            .map(CategoryMapper::toCategoryResponse)
            .orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteByCategoryId(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
