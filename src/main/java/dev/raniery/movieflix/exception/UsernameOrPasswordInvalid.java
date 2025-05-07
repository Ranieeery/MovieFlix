package dev.raniery.movieflix.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class UsernameOrPasswordInvalid extends BadCredentialsException {

    public UsernameOrPasswordInvalid(String message) {
        super(message);
    }
}
