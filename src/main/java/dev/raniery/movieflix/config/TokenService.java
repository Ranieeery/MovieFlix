package dev.raniery.movieflix.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import dev.raniery.movieflix.entity.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${movieflix.security.secret}")
    private String secret;

    public String generatedToken(Users user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
            .withSubject(user.getEmail())
            .withClaim("userId", user.getId().toString())
            .withClaim("name", user.getName())
            .withExpiresAt(expiresAt())
            .withIssuedAt(Instant.now())
            .withIssuer("Movieflix auth API")
            .sign(algorithm);
    }

    private Instant expiresAt() {
//        Instant.now().plusSeconds(43200);
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}