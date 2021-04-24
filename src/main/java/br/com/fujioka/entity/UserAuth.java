package br.com.fujioka.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Marco Aurelio
 * Classe que implementa UserDetails(exigencia do framework), sera usada para gerenciar o usuario durante o processo de autenticação.
 * Neste cenario, onde o dado de entrada (usuario) sera um tipo inteiro, é feita a conversão para String, pois a classe UserDetails exige este tipo.
 * A classe recebe em seu construtor uma matricula, senhae uma lista de perfis, a matricula no construtor é convertida para o tipo exigido por UserDetails (String), 
 * e a lista de perfis tambem é convertida de um List para um Collection<? extends GrantedAuthority> exigido por UserDetails.
 * É possivel gerenciar diversas regras de negocio referente a conta do usuario, atraves dos metodos sobrepostos da classe pai.
 */
public class UserAuth implements UserDetails  {


    private static final long serialVersionUID = 1L;

    private String matricula;
    private String senha;
    private Collection<? extends GrantedAuthority> authorities;

    public UserAuth(){

    }

    public UserAuth(Integer matricula, String senha, List<Perfil> authorities){
        this.matricula = String.valueOf(matricula);
        this.senha = senha;
        this.authorities = authorities.stream()
        .map(p -> new SimpleGrantedAuthority(p.getPerfil().getDescricao()))
        .collect(Collectors.toList());
    }


    public Integer getId(){
        return Integer.parseInt(this.matricula);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        return this.authorities;
    
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.matricula;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
    
}
