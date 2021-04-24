package br.com.fujioka.enumerator;

import com.fasterxml.jackson.annotation.JsonValue;

import org.springframework.http.HttpStatus;

import br.com.fujioka.exception.NegocioException;

import java.lang.reflect.Field;

public enum PerfilEnum {

    ADMIN ("ADMIN","ROLE_ADMIN"),
    COLABORADOR ("COLAB","ROLE_COLABORADOR");


    private final String codigo;
    private final String descricao;


    private PerfilEnum(String codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;

        try{
            Field field = this.getClass().getSuperclass().getDeclaredField("name");
            field.setAccessible(true);
            field.set(this, this.codigo);


        }catch(Exception e){
            e.printStackTrace();
            System.out.println("PerfiEnum: "+e.getMessage());
            new NegocioException("Erro ao buscar  o perfil do usuario", HttpStatus.FORBIDDEN);

        }
    }

    public String getDescricao(){
        return this.descricao;
    }

    @JsonValue
    public String getCodigo(){
        return this.codigo;
    }


  
}
