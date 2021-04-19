package br.com.fujioka.exception.entity;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class CamposErroHandler {

	private String campo;
	private String mensagem;

	public CamposErroHandler(String campo, String mensagem) {
		this.campo = campo;
		this.mensagem= mensagem;
	
		
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
		

	
}
