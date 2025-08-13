package com.project.ecommerce_product_catalog.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;
import java.util.HashMap;

@Component
public class JwtUtil {
//    private final Key key= Keys.secretKeyFor(SignatureAlgorithm.HS256);
    @Value("${jwt.secret}")
    private String key;
    @Value("${jwt.expiration}")
    private  long expired_time;
    @Value("${jwt.refreshExpiration}")
    private  long refresh_time;

    public HashMap<String,String> generateToken(String username){
        HashMap<String ,String> map=new HashMap<>();
        String accessToken= Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date((System.currentTimeMillis()+expired_time)))
                .signWith(SignatureAlgorithm.HS256,key)
                .setSubject(username)
                .compact();
        String refreshToken= Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date((System.currentTimeMillis()+refresh_time)))
                .signWith(SignatureAlgorithm.HS256,key)
                .setSubject(username)
                .compact();
        map.put("ACCESSTOKEN",accessToken);
        map.put("REFRESHTOKEN",refreshToken);
        return map;
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
