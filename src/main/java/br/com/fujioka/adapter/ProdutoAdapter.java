package br.com.fujioka.adapter;

import java.util.ArrayList;
import java.util.List;

import br.com.fujioka.builder.ProdutoDTOBuilder;
import br.com.fujioka.dto.ProdutoDTO;
import br.com.fujioka.dto.ProdutoInputDTO;
import br.com.fujioka.entity.Produto;

public class ProdutoAdapter extends ProdutoDTO {

	private Produto produto;
	private List<Produto> produtos;

	public ProdutoAdapter(Produto produto) {
		this.produto = produto;
	}
	
	public ProdutoAdapter(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public ProdutoDTO converterDTO() {
		if (this.produto != null) {
			setCodigoProduto(this.produto.getCodigoProduto());
			setNomeProduto(this.produto.getNomeProduto());
			setDataRegistro(this.produto.getDataRegistro());
			setGrupo(new GrupoAdapter(this.produto.getGrupo()).converterSimplesDTO());
			setSituacao(this.produto.getSituacao());

			return this;
		}

		return null;

	}

	public List<ProdutoDTO> converterListDTO() {
		if (this.produtos != null) {
			List<ProdutoDTO> produtosDTO = new ArrayList<>();
			ProdutoDTOBuilder builder = new ProdutoDTOBuilder();
			this.produtos.forEach(p -> {
				produtosDTO.add(builder.codigoProduto(p.getCodigoProduto()).dataRegistro(p.getDataRegistro())
						.grupo(new GrupoAdapter(p.getGrupo()).converterSimplesDTO()).nomeProduto(p.getNomeProduto())
						.situacao(p.getSituacao()).build());
			});
			
			return produtosDTO;
		}
		return new ArrayList<>();
	}
	
	public Produto converterProduto(ProdutoInputDTO dto) {
		if (this.produto == null) {
			this.produto = new Produto();
		}
		
		this.produto.setCodigoProduto(dto.getCodigoProduto());
		this.produto.setDataRegistro(dto.getDataRegistro());
		this.produto.setNomeProduto(dto.getNomeProduto());
		this.produto.setGrupo(new GrupoAdapter().converterGrupo(dto.getGrupo()));
		this.produto.setFuncionario(new FuncionarioAdapter().convertFuncionario(dto.getFuncionario()));
		
		return null;
	}

}
