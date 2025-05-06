package dev.raniery.movieflix.service;

import dev.raniery.movieflix.entity.Streaming;
import dev.raniery.movieflix.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final StreamingRepository repository;

    public Streaming save(Streaming streaming) {
        return repository.save(streaming);
    }

    public List<Streaming> findAll() {
        return repository.findAllByOrderByIdAsc();
    }

    public Optional<Streaming> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<Streaming> update(Long id, Streaming streaming) {
        Optional<Streaming> optional = repository.findById(id);

        optional.ifPresent(c -> {
            c.setTitle(streaming.getTitle());
            repository.save(c);
        });

        return optional;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
