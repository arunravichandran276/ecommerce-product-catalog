package com.project.ecommerce_product_catalog.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {
    private final Key key= Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long expired_time=1000*60*60;

    public String generateToken(String username){
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date((System.currentTimeMillis()+expired_time)))
                .signWith(key)
                .setSubject(username)
                .compact();
    }
    public String extractUsername(String token){
        return extractClaim(token,claim->claim.getSubject());
    }
    public <T> T extractClaim(String token,Function<Claims,T> claimResolver){
        Claims claims=extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public boolean validJwtToken(String token,String username){
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token,claims -> claims.getExpiration()).before(new Date());
    }
}
