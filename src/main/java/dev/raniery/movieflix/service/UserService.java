package dev.raniery.movieflix.service;

import dev.raniery.movieflix.entity.Users;
import dev.raniery.movieflix.exception.EmailAlreadyExistsException;
import dev.raniery.movieflix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Users save(Users user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException("Email already registered");
        }

        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));

        return userRepository.save(user);
    }
}
