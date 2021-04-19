package br.com.fujioka.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.fujioka.entity.groups.ConverterGroupItem;

@JsonInclude(value = Include.NON_NULL)
@Entity(name = "tb_grupo")
public class Grupo {

	@Id
	@Column(name = "cd_grupo")
	@NotNull(groups = ConverterGroupItem.Grupo.class, message = "Id do grupo não pode ser vazio")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigoGrupo;
	
	@Column(name = "nm_grupo")
	@NotBlank(message = "Deve ser informado um nome para o grupo")
	@Size(max = 15, message = "Campo deve ter no maximo 15 caracteres")
	private String nomeGrupo;
	
	@Column(name = "dt_alteracao")
	private Date dataAlteracao;

	@OneToOne()
	@JoinColumn(name = "nr_matricula")
	private Funcionario funcionario;


	public Integer getCodigoGrupo() {
		return codigoGrupo;
	}

	public void setCodigoGrupo(Integer codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}

	public String getNomeGrupo() {
		return nomeGrupo;
	}

	public void setNomeGrupo(String nomeGrupo) {
		this.nomeGrupo = nomeGrupo;
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

}
