package br.com.fujioka.config.security;

import java.io.Serializable;

/**
 * @author Marco Aurelio
 * Classe java que sera o dto (data transfer object) ou seja receberar os dados de login e senha do usuario.
 * A classe deve implementar Serializable, por questões de performance.
 */
public class CredenciaisDTO implements Serializable{

    
    private static final long serialVersionUID = 1L;

    private Integer matricula;
    private String senha;


    public void setMatricula(Integer matricula){
        this.matricula = matricula;
    }

    public Integer getMatricula(){
        return this.matricula;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public String getSenha(){
        return this.senha;
    }
    
}
