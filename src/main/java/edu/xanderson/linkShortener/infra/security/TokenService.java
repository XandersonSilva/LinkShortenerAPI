package edu.xanderson.linkShortener.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import edu.xanderson.linkShortener.model.UserEntity;

@Service
public class TokenService {
    @Value("${token.secret.key}")
    private String key;

    public String generateToken(UserEntity user){
        Algorithm algorithm = Algorithm.HMAC256(key);
        try {
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(setExpirationDate())
                    .sign(algorithm);
        } catch (Exception e) {
            throw new RuntimeException("Error while generating token\n", e);
        }
    }

    public String verifyToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(key);
            return JWT.require(algorithm)
                                .withIssuer("auth-api")
                                .build()
                                .verify(token)
                                .getSubject();
        } catch (JWTCreationException e) {
            return "";
        }
    }


    private Instant setExpirationDate(){
        return LocalDateTime.now().plusDays(7).toInstant(ZoneOffset.of("-03:00"));
    }
}
