package br.com.fujioka.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class GrupoDTO {
	
	private Integer codigoGrupo;
	private String nomeGrupo;
	private Date dataCriacao;
	
	
	private FuncionarioSimplesDTO funcionario;

	public GrupoDTO() {
		
	}

	public GrupoDTO(Integer codigoGrupo, String nomeGrupo, Date dataCriacao, FuncionarioSimplesDTO funcionario) {
		super();
		this.codigoGrupo = codigoGrupo;
		this.nomeGrupo = nomeGrupo;
		this.dataCriacao = dataCriacao;
		this.funcionario = funcionario;
	}


	public Integer getCodigoGrupo() {
		return codigoGrupo;
	}


	public void setCodigoGrupo(Integer codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}


	public String getNomeGrupo() {
		return nomeGrupo;
	}


	public void setNomeGrupo(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
	}


	public Date getDataCriacao() {
		return dataCriacao;
	}


	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}


	public FuncionarioSimplesDTO getFuncionario() {
		return funcionario;
	}


	public void setFuncionario(FuncionarioSimplesDTO funcionario) {
		this.funcionario = funcionario;
	}
	
	
	

}
