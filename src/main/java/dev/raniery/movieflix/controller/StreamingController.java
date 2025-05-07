package dev.raniery.movieflix.controller;

import dev.raniery.movieflix.controller.request.StreamingRequest;
import dev.raniery.movieflix.controller.response.StreamingResponse;
import dev.raniery.movieflix.entity.Streaming;
import dev.raniery.movieflix.mapper.StreamingMapper;
import dev.raniery.movieflix.service.StreamingService;
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
@RequestMapping("/movieflix/streaming")
@Tag(name = "Streaming", description = "CRUD operations for streaming platforms")
public class StreamingController {

    private final StreamingService streamingService;

    @PostMapping
    @Operation(
        summary = "Create streaming platform",
        description = "Creates a new streaming platform in the system",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Streaming platform created successfully",
            content = @Content(schema = @Schema(implementation = StreamingResponse.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid streaming platform data provided",
            content = @Content
        )
    })
    public ResponseEntity<StreamingResponse> save(@Valid @RequestBody StreamingRequest request) {
        Streaming savedStreaming = streamingService.save(StreamingMapper.toStreaming(request));

        return ResponseEntity
            .created(URI.create("/movieflix/streaming/" + savedStreaming.getId()))
            .body(StreamingMapper.toStreamingResponse(savedStreaming));
    }

    @GetMapping
    @Operation(
        summary = "List all streaming platforms",
        description = "Retrieves a list of all streaming platforms available in the system",
        security = @SecurityRequirement(name = "bearerAuth")
        
    )
    @ApiResponse(
        responseCode = "200",
        description = "Successfully retrieved streaming platforms",
        content = @Content(schema = @Schema(implementation = StreamingResponse.class))
    )
    public ResponseEntity<List<StreamingResponse>> getAll() {
        List<StreamingResponse> streaming = streamingService
            .findAll()
            .stream()
            .map(StreamingMapper::toStreamingResponse)
            .toList();

        return ResponseEntity.ok(streaming);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Get streaming platform by ID",
        description = "Retrieves a specific streaming platform by its ID",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Streaming platform found",
            content = @Content(schema = @Schema(implementation = StreamingResponse.class))
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Streaming platform not found",
            content = @Content
        )
    })
    public ResponseEntity<StreamingResponse> getById(@PathVariable Long id) {
        return streamingService
            .findById(id)
            .map(c -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(c)))
            .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Update streaming platform",
        description = "Updates an existing streaming platform's information",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Streaming platform updated successfully",
            content = @Content(schema = @Schema(implementation = StreamingResponse.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid streaming platform data provided",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Streaming platform not found",
            content = @Content
        )
    })
    public ResponseEntity<StreamingResponse> update(@PathVariable Long id, @Valid @RequestBody StreamingRequest request) {

        return streamingService.update(id, StreamingMapper.toStreaming(request))
            .map(c -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(c)))
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete streaming platform",
        description = "Removes a streaming platform from the system",
        security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Streaming platform deleted successfully",
            content = @Content
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Streaming platform not found",
            content = @Content
        )
    })
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Optional<Streaming> optionalStreaming = streamingService.findById(id);

        if (optionalStreaming.isPresent()) {
            streamingService.delete(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
