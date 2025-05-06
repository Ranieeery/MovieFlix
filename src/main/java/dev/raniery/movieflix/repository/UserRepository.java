package dev.raniery.movieflix.repository;

import dev.raniery.movieflix.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<UserDetails> findUsersByEmail(String email);
}
