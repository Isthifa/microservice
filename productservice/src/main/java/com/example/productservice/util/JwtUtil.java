//package com.example.productservice.util;
//
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.List;
//import java.util.function.Function;
//
//@Component
//public class JwtUtil {
//    public static final String SECRET_KEY = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//    private Key getSignKey() {
//        byte[] key= Decoders.BASE64.decode(SECRET_KEY); // decode the secret key from base64
//        return Keys.hmacShaKeyFor(key); // return the secret key as a Key object
//    }
//    public void validateToken(final String token) {
//        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
//    }
//    public boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    public boolean validateToken(String token, String username) {
//        final String tokenUsername = extractUsername(token);
//        return (tokenUsername.equals(username) && !isTokenExpired(token));
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(getSignKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    public List<String> extractAuthorities(String token) {
//        return extractClaim(token, claims -> claims.get("authorities", List.class));
//    }
//}