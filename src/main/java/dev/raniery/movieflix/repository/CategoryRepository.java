package dev.raniery.movieflix.repository;

import dev.raniery.movieflix.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
