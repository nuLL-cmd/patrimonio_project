package br.com.fujioka.dto;

import java.sql.Date;

import br.com.fujioka.enumerator.SituacaoEnum;

public class ProdutoDTO {
	
	private Integer codigoProduto;
	private String nomeProduto;
	private Date dataRegistro;

	
	private SituacaoEnum situacao;
		
	private GrupoSimplesDTO grupo;
	

	
	public ProdutoDTO(Integer codigoProduto, String nomeProduto, Date dataRegistro, SituacaoEnum situacao,
			GrupoSimplesDTO grupo) {
		super();
		this.codigoProduto = codigoProduto;
		this.nomeProduto = nomeProduto;
		this.dataRegistro = dataRegistro;
		this.situacao = situacao;
		this.grupo = grupo;
	}

	public ProdutoDTO() {}
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


	public GrupoSimplesDTO getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoSimplesDTO grupo) {
		this.grupo = grupo;
	}

	
	
	
}
