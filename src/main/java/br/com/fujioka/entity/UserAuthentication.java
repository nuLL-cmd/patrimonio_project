package br.com.fujioka.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class UserAuthentication implements UserDetails  {


    private static final long serialVersionUID = 1L;

    private String matricula;
    private String senha;
    private Collection<? extends GrantedAuthority> authorities;

    public UserAuthentication(){

    }

    public UserAuthentication(Integer matricula, String senha, List<Perfil> authorities){
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
