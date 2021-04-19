package br.com.fujioka.exception.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class ErroHandler {
	private Integer status;
	private String message;
	private String data;

	private List<CamposErroHandler> camposErro;
	private String campo;

	public ErroHandler() {

	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String titulo) {
		this.message = titulo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<CamposErroHandler> getCamposErro() {
		return camposErro;
	}

	public void setCamposErro(List<CamposErroHandler> camposErro) {
		this.camposErro = camposErro;
	}


	public String getCampo() {
		return campo;
	}


	public void setCampo(String campo) {
		this.campo = campo;
	}




}
