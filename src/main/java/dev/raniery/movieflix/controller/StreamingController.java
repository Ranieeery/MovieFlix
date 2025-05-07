package dev.raniery.movieflix.controller;

import dev.raniery.movieflix.controller.request.StreamingRequest;
import dev.raniery.movieflix.controller.response.StreamingResponse;
import dev.raniery.movieflix.entity.Streaming;
import dev.raniery.movieflix.mapper.StreamingMapper;
import dev.raniery.movieflix.service.StreamingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movieflix/streaming")
public class StreamingController {

    private final StreamingService streamingService;

    @GetMapping
    public ResponseEntity<List<StreamingResponse>> getAll() {
        List<StreamingResponse> streaming = streamingService
            .findAll()
            .stream()
            .map(StreamingMapper::toStreamingResponse)
            .toList();

        return ResponseEntity.ok(streaming);
    }

    @PostMapping
    public ResponseEntity<StreamingResponse> save(@Valid @RequestBody StreamingRequest request) {
        Streaming savedStreaming = streamingService.save(StreamingMapper.toStreaming(request));

        return ResponseEntity
            .created(URI.create("/movieflix/streaming/" + savedStreaming.getId()))
            .body(StreamingMapper.toStreamingResponse(savedStreaming));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getById(@PathVariable Long id) {
        return streamingService
            .findById(id)
            .map(c -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(c)))
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StreamingResponse> update(@PathVariable Long id,@Valid @RequestBody StreamingRequest request) {

        return streamingService.update(id, StreamingMapper.toStreaming(request))
            .map(c -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(c)))
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Optional<Streaming> optionalStreaming = streamingService.findById(id);

        if (optionalStreaming.isPresent()) {
            streamingService.delete(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
