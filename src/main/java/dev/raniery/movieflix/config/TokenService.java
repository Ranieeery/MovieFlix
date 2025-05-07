package dev.raniery.movieflix.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import dev.raniery.movieflix.entity.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

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

    public Optional<JWTUserData> verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT verify = JWT.require(algorithm).build().verify(token);

            return Optional.of(JWTUserData.builder()
                .id(verify.getClaim("userId").asLong())
                .name(verify.getClaim("name").asString())
                .email(verify.getSubject())
                .build());

        } catch (JWTVerificationException e) {
            return Optional.empty();
        }
    }

    private Instant expiresAt() {
//        Instant.now().plusSeconds(43200);
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}