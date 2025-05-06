package dev.raniery.movieflix.mapper;

import dev.raniery.movieflix.controller.request.CategoryRequest;
import dev.raniery.movieflix.controller.response.CategoryResponse;
import dev.raniery.movieflix.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static Category toCategory(CategoryRequest categoryRequest) {
        return Category
            .builder()
            .name(categoryRequest.name())
            .build();
    }

    public static CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse
            .builder()
            .id(category.getId())
            .name(category.getName())
            .build();
    }
}
