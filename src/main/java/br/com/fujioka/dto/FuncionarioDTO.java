package br.com.fujioka.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.fujioka.entity.Contato;
import br.com.fujioka.entity.Endereco;
import br.com.fujioka.enumerator.SituacaoEnum;

@JsonInclude(value = Include.NON_NULL)
public class FuncionarioDTO {

	private Integer matricula;
	private String usuario;
	private String nome;
	private SituacaoEnum situacao;

	private Endereco endereco;
	
	private Contato contato;

	public FuncionarioDTO() {
	}

	public FuncionarioDTO(Integer matricula, String usuario, String nome
			, SituacaoEnum situacao, Endereco endereco, Contato contato) {
		this.matricula = matricula;
		this.usuario = usuario;
		this.nome = nome;
		this.situacao = situacao;
		this.endereco = endereco;
		this.contato = contato;
	}

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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}
	
	

}
