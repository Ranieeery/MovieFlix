package dev.raniery.movieflix.controller.request;

import jakarta.validation.constraints.Email;

public record UserRequest(
    String name,

    @Email String email,

    String password) {
}
