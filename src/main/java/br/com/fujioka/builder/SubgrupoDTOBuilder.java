package br.com.fujioka.builder;

import java.sql.Date;

import br.com.fujioka.dto.FuncionarioSimplesDTO;
import br.com.fujioka.dto.GrupoSimplesDTO;
import br.com.fujioka.dto.SubgrupoDTO;

public class SubgrupoDTOBuilder {
	
	private Integer codigo;
	private String nomeSubgrupo;
	private Date dataCriacao;
	private GrupoSimplesDTO grupo;
	private FuncionarioSimplesDTO funcionario;
	
	
	public SubgrupoDTO builder() {
		return new SubgrupoDTO(this.codigo, this.nomeSubgrupo, this.dataCriacao
				,this.grupo, this.funcionario);
	}
	
	
	public SubgrupoDTOBuilder codigo(Integer codigo) {
		this.codigo = codigo;
		return this;
	}
	
	public SubgrupoDTOBuilder nomeSubgrupo(String nomeSubgrupo) {
		this.nomeSubgrupo = nomeSubgrupo;
		return this;
	}
	
	public SubgrupoDTOBuilder dataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
		return this;
	}
	
	public SubgrupoDTOBuilder grupo(GrupoSimplesDTO grupo) {
		this.grupo = grupo;
		return this;
	}
	
	public SubgrupoDTOBuilder funcionario(FuncionarioSimplesDTO funcionario) {
		this.funcionario = funcionario;
		return this;
	}


}
