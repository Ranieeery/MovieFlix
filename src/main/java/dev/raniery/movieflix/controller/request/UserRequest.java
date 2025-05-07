package dev.raniery.movieflix.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(

    @NotBlank(message = "User name can't be blank or null")
    String name,

    @Email(message = "Must be a valid email address format")
    @NotBlank(message = "Email can't be blank or null")
    String email,

    @NotBlank(message = "Password can't be blank or null")
    String password) {
}
