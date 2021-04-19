package br.com.fujioka.dto;

import java.sql.Date;

public class SubgrupoDTO {

	private Integer codigo;
	private String nomeSubgrupo;
	private Date dataCriacao;
	private GrupoSimplesDTO grupo;
	private FuncionarioSimplesDTO funcionario;

	public SubgrupoDTO(Integer codigo, String nomeSubgrupo, Date dataCriacao, 
			GrupoSimplesDTO grupo,
			FuncionarioSimplesDTO funcionario) {
		this.codigo = codigo;
		this.nomeSubgrupo = nomeSubgrupo;
		this.dataCriacao = dataCriacao;
		this.grupo = grupo;
		this.funcionario = funcionario;
	}

	public SubgrupoDTO() {
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return this.codigo;
	}

	public void setNomeSubgrupo(String nomeSubgrupo) {
		this.nomeSubgrupo = nomeSubgrupo;
	}

	public String getNomeSubgrupo() {
		return this.nomeSubgrupo;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataCriacao() {
		return this.dataCriacao;
	}

	public void setGrupo(GrupoSimplesDTO grupo) {
		this.grupo = grupo;
	}

	public GrupoSimplesDTO getGrupo() {
		return this.grupo;
	}

	public FuncionarioSimplesDTO getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioSimplesDTO funcionario) {
		this.funcionario = funcionario;
	}

}
