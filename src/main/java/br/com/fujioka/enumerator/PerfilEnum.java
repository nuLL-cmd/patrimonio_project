package br.com.fujioka.enumerator;

import com.fasterxml.jackson.annotation.JsonValue;

import java.lang.reflect.Field;

public enum PerfilEnum {

    ADMIN ("ADM","ROLE_ADMIN"),
    COLABORADOR ("COLAB","ROLE_COLABORADOR");


    private final String codigo;
    private final String descricao;


    private PerfilEnum(String descricao, String codigo){
        this.codigo = codigo;
        this.descricao = descricao;

        try{
            Field field = this.getClass().getSuperclass().getDeclaredField("name");
            field.setAccessible(true);
            field.set(this, this.codigo);


        }catch(Exception e){
            e.printStackTrace();
            System.out.println("PerfiEnum: "+e.getMessage());
        }
    }

    @JsonValue
    public String getDescricao(){
        return descricao;
    }
  
}
