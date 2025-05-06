package dev.raniery.movieflix.repository;

import dev.raniery.movieflix.entity.Streaming;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StreamingRepository extends JpaRepository<Streaming, Long> {
    List<Streaming> findAllByOrderByIdAsc();
}
