package br.com.fujioka.dto;

import java.io.Serializable;

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
