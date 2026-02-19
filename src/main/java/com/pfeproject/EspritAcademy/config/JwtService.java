package com.pfeproject.EspritAcademy.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "A6bm8UzVwK+reMhWQ9vNxiipUT5ueuo6fY+QtMvnmC+4+JUuIjakUl8mocUVbUus54RPKA882jbZlRqkn0Jenb24BoNsdQ/vzD4IZACyO8y0oo3GuQxUC5ljckB5M3DAIERUQYvdWnUaeemxRvLCd0FrajDJB8O3SZY5HRZTBwx32vc3hCgcwQ5obehe5D21TeL+2BvTA701847mmZZtjks2FN+0vrJowytKJElxTIMqv3FYAbOWNsQA2BUE8mg/wikh5Y5HZvXT2UfuvqndIgAtN3oKBRx/6Laf5Ukqi9SIYceyccOrmani8osOUgdfyCUvHMVavVxc4QwWs+eCoY2fr9Dsr44n7+4d8fc/d0E=";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetail) {
        return generateToken(new HashMap<>(), userDetail);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 hours
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // validate a token
    public boolean IsTokenValid(String token, UserDetails userDetail) {
        final String username = extractUsername(token);
        return (username.equals(userDetail.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
