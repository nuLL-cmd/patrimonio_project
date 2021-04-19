package br.com.fujioka.builder;

import br.com.fujioka.dto.FuncionarioDTO;
import br.com.fujioka.entity.Contato;
import br.com.fujioka.entity.Endereco;
import br.com.fujioka.enumerator.SituacaoEnum;

public class FuncionarioDTOBuilder {

	private Integer matricula;
	private String usuario;
	private String nome;
	private SituacaoEnum situacao;
	
	private Endereco endereco;
	
	private Contato contato;
	
	public FuncionarioDTO build() {
		return new FuncionarioDTO(this.matricula,this.usuario,
				this.nome, this.situacao, this.endereco, this.contato);
	}
	
	
	public FuncionarioDTOBuilder matricula(Integer matricula) {
		this.matricula = matricula;
		return this;
	}
	
	public FuncionarioDTOBuilder nome(String nome) {
		this.nome = nome;
		return this;
	}
	
	public FuncionarioDTOBuilder situacao(SituacaoEnum situacao) {
		this.situacao = situacao;
		return this;
	}
	
	public FuncionarioDTOBuilder endereco(Endereco endereco) {
		this.endereco = endereco;
		return this;
	}
	
	public FuncionarioDTOBuilder contato(Contato contato) {
		this.contato = contato;
		return this;
	}
	
	public FuncionarioDTOBuilder usuario(String usuario) {
		this.usuario = usuario;
		return this;
	}
}
