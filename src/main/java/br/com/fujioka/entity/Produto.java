package br.com.fujioka.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.fujioka.enumerator.SituacaoEnum;

@Entity(name = "tb_produto")
@Table(schema = "dbo")
@JsonInclude(value = Include.NON_NULL)
public class Produto {

	@Id
	@Column(name = "nr_produto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigoProduto;

	@Column(name = "nm_produto")
	private String nomeProduto;


	@Column(name = "dt_cadastro")
	private Date dataRegistro;

	@Column(name = "st_artigo")
	@Enumerated(EnumType.STRING)
	private SituacaoEnum situacao;

	@OneToOne
	@JoinColumn(name = "nr_matricula")
	private Funcionario funcionario;

	@OneToOne
	@JoinColumn(name = "cd_grupo")
	private Grupo grupo;

	@OneToOne
	@JoinColumn(name = "cd_subgrupo")
	private SubGrupo subgrupo;
	
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

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public SubGrupo getSubgrupo() {
		return subgrupo;
	}

	public void setSubgrupo(SubGrupo subgrupo) {
		this.subgrupo = subgrupo;
	}
	

}
