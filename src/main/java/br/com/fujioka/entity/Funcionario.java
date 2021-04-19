package br.com.fujioka.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.fujioka.entity.groups.ConverterGroupItem;
import br.com.fujioka.enumerator.GeneroEnum;
import br.com.fujioka.enumerator.SituacaoEnum;

@JsonInclude(value = Include.NON_NULL)
@NotNull(message = "Objeto pessoa não pode ser nulo")
@Entity(name = "tb_funcionario")
public class Funcionario {

	@Id
	@Column(name = "nr_matricula")
	@NotNull(groups = ConverterGroupItem.Funcionario.class, message = "Id do funcionario não pode ser vazio")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer matricula;
	
	@Column(name = "nm_funcionario")
	@NotBlank(message = "Campo nome não pode ser vazio")
	@Size(min = 10, max = 50, message = "Campo deve conter entre 10 e 50 caracteres")
	private String nomeFuncionario;

	@Enumerated(EnumType.STRING)
	@Column(name = "st_funcionario")
	private SituacaoEnum situacao;

	@NotBlank(message = "Campo senha não pode ser vazio")
	@Size(min = 2, max = 15, message = "Campo senha deve ter entre 2 e 15 caractreres")
	private String senha;
	
	@Column(name = "dt_alteracao")
	private Date dataAlteracao;
	
	
	@NotBlank(message =  "Campo RG não pode ser nulo")
	@Size(max = 20, message = "RG deve ter no maximo 20 digitos" )
	private String rg;
	
	@NotBlank(message = "Campo não pode servazio")
	@Size(min = 11, max = 11, message = "CPF deve conter 11 digitos")
	private String cpf;


	@Enumerated(EnumType.STRING)
	private GeneroEnum genero;

	@NotBlank(message = "Campo usuario não pode ser vazio")
	@Column(name = "nm_usuario")
	private String usuario;


	@Valid
	@OneToOne(cascade =  CascadeType.ALL)
	@NotNull(message = "Precisa ser informado um endereço")
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;

	
	@Valid
	@OneToOne(cascade =  CascadeType.ALL)
	@JoinColumns(value = {
			@JoinColumn(name = "ddd"), @JoinColumn(name = "telefone")})
	private Contato contato;


	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public SituacaoEnum getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoEnum situacao) {
		this.situacao = situacao;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}
	
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public GeneroEnum getGenero() {
		return genero;
	}

	public void setGenero(GeneroEnum genero) {
		this.genero = genero;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Contato getContato() {
		return contato;
	}


	public void setContato(Contato contato) {
		this.contato = contato;
	}
	
	
	
	

	


}
