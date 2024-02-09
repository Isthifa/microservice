package com.example.iam.jwt.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {
    private static final String SECRET_KEY ="5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    public void validateToken(String token) {
        Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token); // parse the token. If not a valid token, it will throw an exception
    }

    public String generateToken(Authentication authentication) {
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("authorities", authentication.getAuthorities())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10)) // token will expire after 10 hours
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    // get the secret key
    private Key getSignKey() {
        byte[] key= Decoders.BASE64.decode(SECRET_KEY); // decode the secret key from base64
        return Keys.hmacShaKeyFor(key); // return the secret key as a Key object
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
