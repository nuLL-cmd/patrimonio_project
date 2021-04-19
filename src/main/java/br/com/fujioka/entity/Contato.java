package br.com.fujioka.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.fujioka.entity.pk.ContatoId;

@Entity(name = "tb_contato")
@Table(schema = "dbo")
@JsonInclude(value = Include.NON_NULL)
@IdClass(ContatoId.class)
public class Contato {

	@Id
	@NotBlank(message = "O funcionario precisa ter um contato com ddd e telefone" )
	private String ddd;
	
	@Id
	@NotBlank(message = "funcionario precsia ter um contato com ddd e telefone")
	private String telefone;
	
	@NotBlank(message = "Campo não pode estar vazio")
	@Size(min = 10, max = 50, message = "Campo deve ter no minimo 10 e no maximo 50 caracteres")
	private String nome;
	
	private String email;



	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
}
