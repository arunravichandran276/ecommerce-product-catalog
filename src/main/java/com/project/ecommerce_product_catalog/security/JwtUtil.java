package com.project.ecommerce_product_catalog.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

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

}
