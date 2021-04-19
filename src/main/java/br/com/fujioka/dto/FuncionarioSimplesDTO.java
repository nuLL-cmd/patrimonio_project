package br.com.fujioka.dto;

import br.com.fujioka.enumerator.SituacaoEnum;

public class FuncionarioSimplesDTO {

	private Integer matricula;
	private String usuario;
	private String nome;
	
	private SituacaoEnum situacao;
	
	
	public Integer getMatricula() {
		return matricula;
	}
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public SituacaoEnum getSituacao() {
		return situacao;
	}
	public void setSituacao(SituacaoEnum situacao) {
		this.situacao = situacao;
	}
	
	
}
