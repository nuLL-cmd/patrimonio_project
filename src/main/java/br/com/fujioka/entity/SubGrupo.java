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
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.fujioka.entity.groups.ConverterGroupItem;

@Entity(name = "tb_subgrupo")
@Table(schema = "dbo")
@JsonInclude(value = Include.NON_NULL) 
public class SubGrupo {

	@Id
	@Column(name = "cd_subgrupo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigoSubgrupo;

	@Column(name = "nm_subgrupo")
	@NotBlank(message = "Nome do subgrupo não pode ser vazio")
	@Size(min = 5, max = 15, message = "Campo deve conter entre 5 e 15 caracteres")
	private String nomeSubgrupo;
	
	@Column(name = "dt_alteracao")
	private Date dataAlteracao;

	@Valid
	@ConvertGroup(from = Default.class, to = ConverterGroupItem.Grupo.class)
	@OneToOne
	@NotNull(message = "Deve ser informado um grupo para esse subgrupo")
	@JoinColumn(name = "cd_grupo")
	private Grupo grupo;


	
	@OneToOne
	@Valid
	@ConvertGroup(from = Default.class, to = ConverterGroupItem.Funcionario.class)
	@JoinColumn(name = "nr_matricula")
	@NotNull(message = "Deve ser informado um funcionario para esse subgrupo")
	private Funcionario funcionario;
	


	public Integer getCodigoSubgrupo() {
		return codigoSubgrupo;
	}

	public void setCodigoSubgrupo(Integer codigoSubgrupo) {
		this.codigoSubgrupo = codigoSubgrupo;
	}


	public String getNomeSubgrupo() {
		return nomeSubgrupo;
	}

	public void setNomeSubgrupo(String nomeSubgrupo) {
		this.nomeSubgrupo = nomeSubgrupo;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
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

	
	

	
	
}
