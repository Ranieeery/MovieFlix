package dev.raniery.movieflix.controller;

import dev.raniery.movieflix.controller.request.CategoryRequest;
import dev.raniery.movieflix.controller.response.CategoryResponse;
import dev.raniery.movieflix.entity.Category;
import dev.raniery.movieflix.mapper.CategoryMapper;
import dev.raniery.movieflix.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movieflix/category")
@Tag(name = "Category", description = "CRUD operations for movie categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @Operation(
        summary = "List all categories",
        description = "Retrieves a list of all movie categories available in the system",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(
        responseCode = "200",
        description = "Successfully retrieved categories",
        content = @Content(schema = @Schema(implementation = CategoryResponse.class))
    )
    public ResponseEntity<List<CategoryResponse>> getAll() {
        List<CategoryResponse> categories = categoryService
            .findAll()
            .stream()
            .map(CategoryMapper::toCategoryResponse)
            .toList();

        return ResponseEntity.ok(categories);
    }

    @PostMapping
    @Operation(
        summary = "Create category",
        description = "Creates a new movie category in the system",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Category created successfully",
            content = @Content(schema = @Schema(implementation = CategoryResponse.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid category data provided",
            content = @Content
        )
    })
    public ResponseEntity<CategoryResponse> save(@Valid @RequestBody CategoryRequest request) {
        Category savedCategory = categoryService.save(CategoryMapper.toCategory(request));

        return ResponseEntity
            .created(URI.create("/movieflix/category/" + savedCategory.getId()))
            .body(CategoryMapper.toCategoryResponse(savedCategory));
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Get category by ID",
        description = "Retrieves a specific movie category by its ID",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Category found",
            content = @Content(schema = @Schema(implementation = CategoryResponse.class))
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Category not found",
            content = @Content
        )
    })
    public ResponseEntity<CategoryResponse> getById(@PathVariable Long id) {
        return categoryService
            .findById(id)
            .map(c -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(c)))
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Update category",
        description = "Updates an existing movie category's information",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Category updated successfully",
            content = @Content(schema = @Schema(implementation = CategoryResponse.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid category data provided",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Category not found",
            content = @Content
        )
    })
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id, @Valid @RequestBody CategoryRequest request) {

        return categoryService.update(id, CategoryMapper.toCategory(request))
            .map(c -> ResponseEntity.ok(CategoryMapper.toCategoryResponse(c)))
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete category",
        description = "Removes a movie category from the system",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Category deleted successfully",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Category not found",
            content = @Content
        )
    })
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Optional<Category> optionalCategory = categoryService.findById(id);

        if (optionalCategory.isPresent()) {
            categoryService.delete(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
