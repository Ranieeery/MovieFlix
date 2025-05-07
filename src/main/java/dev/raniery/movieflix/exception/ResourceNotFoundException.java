package dev.raniery.movieflix.exception;

public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    public static ResourceNotFoundException forCategoryId(Long id) {
        return new ResourceNotFoundException("Category with ID " + id + " not found");
    }
    
    public static ResourceNotFoundException forStreamingId(Long id) {
        return new ResourceNotFoundException("Streaming with ID " + id + " not found");
    }
}