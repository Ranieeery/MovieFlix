package dev.raniery.movieflix.exception;

public class UsernameOrPasswordInvalid extends RuntimeException {

    public UsernameOrPasswordInvalid(String message) {
        super(message);
    }
}
