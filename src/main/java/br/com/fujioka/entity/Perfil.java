package br.com.fujioka.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.fujioka.enumerator.PerfilEnum;


@Entity
@Table(name = "tb_perfil", schema = "dbo")
@JsonInclude(value = Include.NON_NULL)
public class Perfil {   


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil")
    private Integer idPerfil;

    @Enumerated(EnumType.STRING)
    @Column(name = "nm_perfil")
    private PerfilEnum perfil;


    public Perfil(PerfilEnum perfil){
        this.perfil = perfil;
    }


    public Perfil(){

    }


    public void setIdPerfil(Integer idPerfil){
        this.idPerfil = idPerfil;
    }

    public Integer getIdPerfil(){
        return this.idPerfil;
    }

    public void setPerfil(PerfilEnum perfil){
        this.perfil = perfil;
    }

    public PerfilEnum getPerfil(){
        return this.perfil;
    }


}
