package dev.raniery.movieflix.mapper;

import dev.raniery.movieflix.controller.request.StreamingRequest;
import dev.raniery.movieflix.controller.response.StreamingResponse;
import dev.raniery.movieflix.entity.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    public static Streaming toStreaming(StreamingRequest streamingRequest) {
        return Streaming
            .builder()
            .title(streamingRequest.title())
            .build();
    }

    public static StreamingResponse toStreamingResponse(Streaming streaming) {
        return StreamingResponse
            .builder()
            .id(streaming.getId())
            .title(streaming.getTitle())
            .build();
    }
}
