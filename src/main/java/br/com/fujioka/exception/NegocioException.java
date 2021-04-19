package br.com.fujioka.exception;

import org.springframework.http.HttpStatus;

public class NegocioException extends RuntimeException {
	  
    private static final long serialVersionUID = 1L;
    private final HttpStatus status;
    
    public NegocioException(String mensagem, HttpStatus status) {
        super(mensagem);

        this.status = status;
    }

    public HttpStatus getStatus(){
        return this.status;
    }
}
