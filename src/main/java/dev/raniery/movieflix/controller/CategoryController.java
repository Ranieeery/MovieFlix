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
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movieflix/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAll() {
        List<CategoryResponse> categories = categoryService
            .findAll()
            .stream()
            .map(CategoryMapper::toCategoryResponse)
            .toList();

        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> save(@RequestBody CategoryRequest request) {
        Category savedCategory = categoryService.save(CategoryMapper.toCategory(request));

        return ResponseEntity
            .created(URI.create("/movieflix/category/" + savedCategory.getId()))
            .body(CategoryMapper.toCategoryResponse(savedCategory));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable Long id) {
        return categoryService
            .findById(id)
            .map(c -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(c)))
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id, @RequestBody CategoryRequest request) {

        return categoryService.update(id, CategoryMapper.toCategory(request))
            .map(c -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(c)))
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Optional<Category> optionalCategory = categoryService.findById(id);

        if (optionalCategory.isPresent()) {
            categoryService.delete(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
