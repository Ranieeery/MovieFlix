package dev.raniery.movieflix.repository;

import dev.raniery.movieflix.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {
}
