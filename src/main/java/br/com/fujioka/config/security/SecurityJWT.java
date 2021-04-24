package br.com.fujioka.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class SecurityJWT {
    
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(String matricula){
        return Jwts.builder()
        .setSubject(matricula)
        .setExpiration(new Date(System.currentTimeMillis()+expiration))
        .signWith(SignatureAlgorithm.HS512, secret.getBytes())
        .compact();
    }
}
