package com.example.auth_api.Services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {
        @Value("${jwt.secret}")
        private String SECRET_KEY;

        public String generateToken(String email) {

                return Jwts.builder()
                        .subject(email)
                        .issuedAt(new Date())
                        .expiration(
                                new Date(
                                        System.currentTimeMillis()
                                                + 1000L * 60 * 60 * 24
                                )
                        )
                        .signWith(getKey())
                        .compact();
        }

        public String extractEmail(String token) {

                return Jwts.parser()
                        .verifyWith(getKey())
                        .build()
                        .parseSignedClaims(token)
                        .getPayload()
                        .getSubject();
        }

        private SecretKey getKey() {

                return Keys.hmacShaKeyFor(
                        SECRET_KEY.getBytes(StandardCharsets.UTF_8)
                );
        }



        public boolean isValid(String token, UserDetails userDetails) {

                String email = extractEmail(token);

                return email.equals(userDetails.getUsername())
                        && !isTokenExpired(token);
        }

        private boolean isTokenExpired(String token) {
                return extractExpiration(token).before(new Date());
        }

        private Date extractExpiration(String token) {
                return Jwts.parser()
                        .verifyWith(getKey())
                        .build()
                        .parseSignedClaims(token)
                        .getPayload()
                        .getExpiration();
        }
}
