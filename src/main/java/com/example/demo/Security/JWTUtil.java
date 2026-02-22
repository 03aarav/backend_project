//package com.example.demo.Security;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import jakarta.annotation.PostConstruct;
//import java.security.Key;
//import java.util.Base64;
//import java.util.Date;
//import java.util.function.Function;
//
//@Component
//public class JWTUtil {
//
//    @Value("${jwt.secret}")
//    private String secret;
//
//    private Key secretKey;
//
//    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour
//
//    @PostConstruct
//    public void init() {
//        byte[] decodedKey = Base64.getDecoder().decode(secret);
//        this.secretKey = Keys.hmacShaKeyFor(decodedKey);
//    }
//
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(secretKey)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//    public String generateToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .signWith(secretKey, SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//
//    public Boolean validateToken(String token, Object userDetails) {
//        final String username = extractUsername(token);
//        return (username.equals(((com.example.demo.Model.User) userDetails).getEmail()) && !isTokenExpired(token));
//    }
//
//    private Boolean isTokenExpired(String token) {
//        final Date expiration = extractClaim(token, Claims::getExpiration);
//        return expiration.before(new Date());
//    }
//}
