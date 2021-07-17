package br.com.fujioka.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author Marco Aurleio
 * Classe que extende de BasicAuthenticationFilter, onde se implementa  um filtro de autorização.
 * Server para pegar o token gerado pelo jwt, e validar se ele é um token valido, verificando tambem o usuario e senha passado.
 * A classe consiste no medod doFilterInternal que faz a validação do token e permite a autorização da aplicação.
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private ConfigJWT security;
    private UserDetailsService userDetailsService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, ConfigJWT security, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.security = security;
        this.userDetailsService = userDetailsService;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException{

        String header = request.getHeader("Authorization");

        if(header != null && header.startsWith("Bearer ")){
            UsernamePasswordAuthenticationToken authToken = getAuthentication(header.substring(7));
            if(authToken != null){
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        chain.doFilter(request, response);
    }


    public UsernamePasswordAuthenticationToken getAuthentication(String token){
        if(security.validToken(token)){
            String matricula = security.getUsername(token);
            UserDetails user = userDetailsService.loadUserByUsername(matricula);
            return new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
        }
        return null;
    }
    
}
