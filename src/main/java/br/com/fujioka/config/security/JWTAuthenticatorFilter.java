package br.com.fujioka.config.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

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
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Marco Aurelio
 * Classe que representara um filtro para a autenticação jwt, essa classe extende de UsernamePasswordAuthenticationFilter (por exigencia da documentação)
 * De modo geral, a classe possui dois metodos:
 * 
 * O metodo attemptAuthentication tenta autenticar com as credenciais passadas atravez da requisição post dentro do objeto request, 
 * tenta gerar um token com os dados obtidos atraves do objeto authToken da classe UsernamePsswordAuthenticationToken,
 *  por fim tenta autenticar atraves do metodo authenticate do objeto authenticationManager da classe AuthenticationManager, retornando essa autenticação.
 * 
 * O meteodo successfulAuthentication gera o token com base na matricula, e grava no cabeçalho da autenticação do tipo "Authorization"  e um value "Bearer + token"
 * 
 */
public class JWTAuthenticatorFilter extends UsernamePasswordAuthenticationFilter {
    
    private AuthenticationManager authenticationManager;
    private ConfigJWT security;

    public JWTAuthenticatorFilter(AuthenticationManager authenticationManager, ConfigJWT security){
        setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
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
        String matricula = ((UserAuth) auth.getPrincipal()).getUsername();
        String token = security.generateToken(matricula);
        response.addHeader("Authorization", "Bearer "+ token);
        
        
    }

    private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler{

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                AuthenticationException exception) throws IOException, ServletException {
                
            response.setStatus(401);
            response.setContentType("application/json");
            response.getWriter().append(json());
            
        }

        private CharSequence json() {
            long date  = new Date().getTime();
            return "{\"timestamp\": "+date +",\"status\":401,\"erro\":\"N\u00e3o autorizado\",\"message\":\"Matricula ou senha iv\u00e1lidos\",\"path\":\"/login\"}";
        }
        
    }

}
