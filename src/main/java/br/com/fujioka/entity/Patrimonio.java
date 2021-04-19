package br.com.fujioka.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Table(schema = "dbo")
@Entity(name = "tb_patrimonio")
@JsonInclude(value = Include.NON_NULL)
public class Patrimonio {

	@Id
	@Column(name = "nr_patrimonio")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigoPatrimonio;

	@Column(name = "nm_patrimonio")
	@NotBlank(message = "Campo não pode estar vazio")
	@Size(max = 100,message = "Campo não pode exceder os 100 caracteres")
	private String nomePatrimonio;

	@Column(name = "dt_tombamento")
	private Date dataTombamento;

	@NotBlank(message = "Campo não pode estar vazio")
	@Size(max = 1000,message =  "Campo não pode exceder os 1000 caracteres")
	private String descricao;
	
	@OneToOne
	@JoinColumn(name = "nr_matricula")
	@JsonIgnoreProperties({"patrimonios","grupos","subgrupos"})
	private Funcionario funcionario;
	
	@OneToOne
	@JoinColumn(name = "nr_produto")
	@JsonIgnoreProperties({"funcionario","grupo","subgrupo"})
	private Produto produto;
	

	public Integer getCodigoPatrimonio() {
		return codigoPatrimonio;
	}

	public void setCodigoPatrimonio(Integer codigoPatrimonio) {
		this.codigoPatrimonio = codigoPatrimonio;
	}

	public String getNomePatrimonio() {
		return nomePatrimonio;
	}

	public void setNomePatrimonio(String nomePatrimonio) {
		this.nomePatrimonio = nomePatrimonio;
	}

	public Date getDataTombamento() {
		return dataTombamento;
	}

	public void setDataTombamento(Date dataTombamento) {
		this.dataTombamento = dataTombamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	

}
