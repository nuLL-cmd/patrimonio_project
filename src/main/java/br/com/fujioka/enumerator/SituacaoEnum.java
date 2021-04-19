package br.com.fujioka.enumerator;

import java.lang.reflect.Field;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SituacaoEnum {
	A("ATIVO","A"),
	B("BAIXADO","B"),
	I("INATIVO","I");
	
	
	private final String codigo;
	private final String descricao;
	
	private SituacaoEnum(String descricao, String codigo){
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
