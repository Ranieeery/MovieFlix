package dev.raniery.movieflix.service;

import dev.raniery.movieflix.entity.Users;
import dev.raniery.movieflix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Users save (Users user) {
        return userRepository.save(user);
    }
}
