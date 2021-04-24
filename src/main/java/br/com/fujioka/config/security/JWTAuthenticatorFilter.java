package br.com.fujioka.config.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.fujioka.dto.CredenciaisDTO;
import br.com.fujioka.entity.UserAuthentication;

public class JWTAuthenticatorFilter extends UsernamePasswordAuthenticationFilter {
    
    private AuthenticationManager authenticationManager;
    private SecurityJWT security;

    public JWTAuthenticatorFilter(AuthenticationManager authenticationManager, SecurityJWT security){
        this.authenticationManager = authenticationManager;
        this.security = security;
    }

    @Override
    
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{
        try {
            CredenciaisDTO cred = new ObjectMapper().readValue(request.getInputStream(),CredenciaisDTO.class);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(cred.getMatricula(), cred.getSenha(), new ArrayList<>());
            Authentication auth = authenticationManager.authenticate(authToken);
            return auth;
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
           
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException{
        String matricula = ((UserAuthentication) auth.getPrincipal()).getUsername();
        String token = security.generateToken(matricula);
        response.addHeader("Authorization", "Bearer "+ token);
        
        
    }

}
