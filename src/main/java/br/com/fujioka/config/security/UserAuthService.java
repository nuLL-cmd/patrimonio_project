package br.com.fujioka.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fujioka.entity.Funcionario;
import br.com.fujioka.exception.NegocioException;
import br.com.fujioka.repository.FuncionarioRepo;

/**
 * @author Marco Aurélio
 * Classe anotada como @Service exigida pelo framework. que implementa UserDetailsService (exigencia do framework), 
 * é no metodo sobreposto loadUserByUserName que deve retornar um UserDetails que possui uma string como parametro, 
 * onde sera feito a logica para buscar no banco os dados do usuario com base na string recebida como parametro (Neste caso )
 * Por fim é retornado um novo UserAuth (pois ele extende de UserDetails) passando em seu construtor a matricula, a senha e a lista de perfis (ja que esta mapeada na classe Funcionario)
 */
@Service
public class UserAuthService implements UserDetailsService{
    
    @Autowired
    private FuncionarioRepo funcionarioRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Funcionario funcionario = funcionarioRepo.findById(Integer.parseInt(username))
        .orElseThrow(() -> new NegocioException("Matricula não encontrada", HttpStatus.UNAUTHORIZED));

        return new UserAuth(
            funcionario.getMatricula(),
            funcionario.getSenha(),
            funcionario.getPerfis()
        );
    }
    
}
