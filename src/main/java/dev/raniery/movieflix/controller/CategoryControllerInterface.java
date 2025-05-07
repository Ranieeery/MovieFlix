package dev.raniery.movieflix.controller;

import dev.raniery.movieflix.controller.request.CategoryRequest;
import dev.raniery.movieflix.controller.response.CategoryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Category", description = "CRUD operations for movie categories")
public interface CategoryControllerInterface {

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
    ResponseEntity<List<CategoryResponse>> getAll();

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
    ResponseEntity<CategoryResponse> save(@Valid @RequestBody CategoryRequest request);

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
    ResponseEntity<CategoryResponse> getById(@PathVariable Long id);

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
    ResponseEntity<CategoryResponse> update(@PathVariable Long id, @Valid @RequestBody CategoryRequest request);

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
    ResponseEntity<Void> deleteById(@PathVariable Long id);
}
