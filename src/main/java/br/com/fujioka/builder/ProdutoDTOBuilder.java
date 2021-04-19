package br.com.fujioka.builder;

import java.sql.Date;

import br.com.fujioka.dto.GrupoSimplesDTO;
import br.com.fujioka.dto.ProdutoDTO;
import br.com.fujioka.enumerator.SituacaoEnum;

public class ProdutoDTOBuilder {

	private Integer codigoProduto;
	private String nomeProduto;
	private Date dataRegistro;
	private SituacaoEnum situacao;
	
	private GrupoSimplesDTO grupo;
	
	
	public ProdutoDTO build() {
		return new ProdutoDTO(this.codigoProduto, this.nomeProduto, this.dataRegistro
				,this.situacao,this.grupo);
	}
	
	public ProdutoDTOBuilder codigoProduto(Integer codigoProduto) {
		this.codigoProduto = codigoProduto;
		return this;
	}
	
	public ProdutoDTOBuilder nomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
		return this;
	}
	
	public ProdutoDTOBuilder dataRegistro(Date dataRegistro){
			this.dataRegistro = dataRegistro;
			return this;
	}
			
	public ProdutoDTOBuilder situacao(SituacaoEnum situacao) {
		this.situacao = situacao;
		return this;
	}
	
	public ProdutoDTOBuilder grupo(GrupoSimplesDTO grupo) {
		this.grupo = grupo;
		return this;
	}
			
			
			
}
