package br.com.fujioka.dto;

import java.sql.Date;

import br.com.fujioka.enumerator.SituacaoEnum;

public class ProdutoInputDTO {

	private Integer codigoProduto;
	private String nomeProduto;
	private Date dataRegistro;
	private SituacaoEnum situacao;

	private FuncionarioSimplesDTO funcionario;

	private GrupoSimplesDTO grupo;

	private SubgrupoSimplesDTO subgrupo;

	public Integer getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(Integer codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Date getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Date dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public SituacaoEnum getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoEnum situacao) {
		this.situacao = situacao;
	}

	public FuncionarioSimplesDTO getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioSimplesDTO funcionario) {
		this.funcionario = funcionario;
	}

	public GrupoSimplesDTO getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoSimplesDTO grupo) {
		this.grupo = grupo;
	}

	public SubgrupoSimplesDTO getSubgrupo() {
		return subgrupo;
	}

	public void setSubgrupo(SubgrupoSimplesDTO subgrupo) {
		this.subgrupo = subgrupo;
	}
	
	

}
