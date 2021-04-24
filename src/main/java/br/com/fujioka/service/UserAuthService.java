package br.com.fujioka.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fujioka.entity.Funcionario;
import br.com.fujioka.entity.UserAuthentication;
import br.com.fujioka.exception.NegocioException;
import br.com.fujioka.repository.FuncionarioRepo;

@Service
public class UserAuthService implements UserDetailsService{
    
    @Autowired
    private FuncionarioRepo funcionarioRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Funcionario funcionario = funcionarioRepo.findById(Integer.parseInt(username))
        .orElseThrow(() -> new NegocioException("Matricula não encontrada", HttpStatus.UNAUTHORIZED));

        return new UserAuthentication(
            funcionario.getMatricula(),
            funcionario.getSenha(),
            funcionario.getPerfis()
        );
    }
    
}
