package br.com.fujioka.entity.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Embeddable
public class ContatoId implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "DDD não pode ser vazio")
	@Size(min = 2, max= 4, message = "DDD deve conter entre 2 e 4 caracteres")
	private String ddd;
	
	@NotBlank(message = "Telefone não pode ser vazio")
	@Size(min=8, max=9, message = "Telefone tem que conter entre 8 e 9 caracteres")
	private String telefone;
	
	
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	
	public String getDdd() {
		return this.ddd;
	}
	
	public void setTelefone(String telefone){
		this.telefone = telefone;
	}
	
	public String getTelefone() {
		return this.telefone;
	}
}
