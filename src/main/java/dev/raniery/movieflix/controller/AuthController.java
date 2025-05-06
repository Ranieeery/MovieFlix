package dev.raniery.movieflix.controller;

import dev.raniery.movieflix.controller.request.UserRequest;
import dev.raniery.movieflix.controller.response.UserResponse;
import dev.raniery.movieflix.entity.Users;
import dev.raniery.movieflix.mapper.UserMapper;
import dev.raniery.movieflix.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("movieflix/auth")
public class AuthController {

    private final UserService userService;

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRequest userRequest) {
        Users saved = userService.save(UserMapper.toUser(userRequest));

        return ResponseEntity.created(URI.create("/movieflix/user/" + saved.getId())).body(UserMapper.toUserResponse(saved));
    }
}
