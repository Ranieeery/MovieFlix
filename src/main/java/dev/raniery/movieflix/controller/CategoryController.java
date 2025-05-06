package dev.raniery.movieflix.controller;

import dev.raniery.movieflix.controller.request.CategoryRequest;
import dev.raniery.movieflix.controller.response.CategoryResponse;
import dev.raniery.movieflix.entity.Category;
import dev.raniery.movieflix.mapper.CategoryMapper;
import dev.raniery.movieflix.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movieflix/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> categories = categoryService
            .findAll()
            .stream()
            .map(CategoryMapper::toCategoryResponse)
            .toList();

        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody CategoryRequest request) {
        Category category = CategoryMapper.toCategory(request);
        Category savedCategory = categoryService.saveCategory(category);


        return ResponseEntity
            .created(URI.create("/movieflix/category/" + savedCategory.getId()))
            .body(CategoryMapper.toCategoryResponse(savedCategory));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getByCategoryId(@PathVariable Long id) {
        return categoryService
            .findById(id)
            .map(c -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(c)))
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByCategoryId(@PathVariable Long id) {
        categoryService.deleteCategory(id);

        return ResponseEntity.noContent().build();
    }
}
