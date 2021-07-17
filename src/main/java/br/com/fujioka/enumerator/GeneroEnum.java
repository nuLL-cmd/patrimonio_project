package br.com.fujioka.enumerator;

import java.lang.reflect.Field;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GeneroEnum {
	
	M("MASCULINO","M"),
	F("FEMININO","F"),
	D("DIVERSOS","D");
	
	
	private  String codigo;
	private  String descricao;
	
	private GeneroEnum(String descricao, String codigo){
		this.codigo = codigo;
		this.descricao = descricao;
		try {
			Field field = this.getClass().getSuperclass().getDeclaredField("name");
			field.setAccessible(true);
			field.set(this, this.codigo);
		}	catch (Exception e) {
			e.printStackTrace();
			System.out.println("GeneroEnum: "+e.getMessage());


		}
	}

	@JsonValue
	public String getDescricao() {
		return descricao;
	
	}
}
