package dev.raniery.movieflix.controller;

import dev.raniery.movieflix.config.TokenService;
import dev.raniery.movieflix.controller.request.LoginRequest;
import dev.raniery.movieflix.controller.request.UserRequest;
import dev.raniery.movieflix.controller.response.LoginResponse;
import dev.raniery.movieflix.controller.response.UserResponse;
import dev.raniery.movieflix.entity.Users;
import dev.raniery.movieflix.mapper.UserMapper;
import dev.raniery.movieflix.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("movieflix/auth")
public class AuthController implements AuthControllerInterface {

    private final UserService userService;
    private final AuthenticationManager authManager;
    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRequest userRequest) {
        Users saved = userService.save(UserMapper.toUser(userRequest));
        return ResponseEntity.created(URI.create("/movieflix/user/" + saved.getId()))
            .body(UserMapper.toUserResponse(saved));

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {

        var authToken = new UsernamePasswordAuthenticationToken(request.email(), request.password());

        Authentication authenticate = authManager.authenticate(authToken);

        Users user = (Users) authenticate.getPrincipal();

        String generatedToken = tokenService.generatedToken(user);

        return ResponseEntity.ok(new LoginResponse(generatedToken));
    }
}
