package br.com.fujioka.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Marco Aurelio
 * Classe de configuração para o JWT, onde é recuperado a palavra secreta e o tempo de expiração,
 * colocados no application.properties da aplicação
 * O metodo generateToken() que recebe uma string contendo a matricula retorna
 * um JTWT configuravel com o subject(matricula), um setExpiration(passando a data atual mais o tempo de expiração)
 * uma assinatura passando o tipo de algoritimo usado e a palavra secreta, compactando assim tudo para a geração do token.
 */
@Component
public class ConfigJWT {
    
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

    public boolean validToken(String token){
        Claims claims = getClaims(token);
        if (claims != null){
            String username = claims.getSubject();
            Date expiration = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());

            if(username != null && expiration != null && now.before(expiration))
                return true;
        }

        return false;
    }
    
    public String getUsername(String token){
        Claims claims = getClaims(token);
        if (claims != null){
            return claims.getSubject();
        }

        return null;
    }

    public Claims getClaims(String token){
        try{
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        }catch(Exception e){
            return null;
        }
       
    }

}
