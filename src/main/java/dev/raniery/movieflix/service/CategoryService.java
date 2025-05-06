package dev.raniery.movieflix.service;

import dev.raniery.movieflix.entity.Category;
import dev.raniery.movieflix.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public Category save(Category category) {
        return repository.save(category);
    }

    public List<Category> findAll() {
        return repository.findAllByOrderByIdAsc();
    }

    public Optional<Category> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<Category> update(Long id, Category category) {
        Optional<Category> optional = repository.findById(id);

        optional.ifPresent(c -> {
            c.setName(category.getName());
            repository.save(c);
        });

        return optional;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
