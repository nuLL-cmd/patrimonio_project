package br.com.fujioka.dto;

import java.sql.Date;

import br.com.fujioka.entity.Grupo;

public class SubgrupoSimplesDTO {

	private Integer codigo;
	private String nomeSubgrupo;
	private Date dataCriacao;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNomeSubgrupo() {
		return nomeSubgrupo;
	}

	public void setNomeSubgrupo(String nomeSubgrupo) {
		this.nomeSubgrupo = nomeSubgrupo;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

}
