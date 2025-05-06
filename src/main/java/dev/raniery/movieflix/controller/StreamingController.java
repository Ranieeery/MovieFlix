package dev.raniery.movieflix.controller;

import dev.raniery.movieflix.controller.request.StreamingRequest;
import dev.raniery.movieflix.controller.response.StreamingResponse;
import dev.raniery.movieflix.entity.Streaming;
import dev.raniery.movieflix.mapper.StreamingMapper;
import dev.raniery.movieflix.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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
    public ResponseEntity<StreamingResponse> save(@RequestBody StreamingRequest request) {
        Streaming streaming = StreamingMapper.toStreaming(request);
        Streaming savedStreaming = streamingService.save(streaming);


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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByStreamingId(@PathVariable Long id) {
        streamingService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
