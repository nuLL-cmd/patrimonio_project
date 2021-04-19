package br.com.fujioka.exception;

import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.fujioka.exception.entity.CamposErroHandler;
import br.com.fujioka.exception.entity.ErroHandler;
import br.com.fujioka.utils.Utilitarios;

@ControllerAdvice
public class ErroExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> processaException(NegocioException ex,WebRequest request){
		HttpStatus status = ex.getStatus();
		ErroHandler erroHandler = new ErroHandler();
		
		erroHandler.setStatus(status.value());
		erroHandler.setMessage(ex.getMessage());
		erroHandler.setData(new SimpleDateFormat("dd-MM-YYY - HH:mm:ss").format((OffsetDateTime.now().toEpochSecond()*1000)));
		
		return handleExceptionInternal(ex, erroHandler, new HttpHeaders(), ex.getStatus(),request);
		
		
	}
	
	@ExceptionHandler( ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolation(
	  ConstraintViolationException ex, WebRequest request) {
	

	    ErroHandler apiError = new ErroHandler();
	    apiError.setStatus(400);
	    apiError.setData(Utilitarios.formateDateTime(OffsetDateTime.now().toEpochSecond()));
	    apiError.setMessage("Atenção para chave duplicada na tabela");
	    apiError.setCampo(ex.getConstraintName());
	    return new ResponseEntity<Object>(
	      apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	@ExceptionHandler( IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(
	  IllegalArgumentException ex, WebRequest request) {
	

	    ErroHandler apiError = new ErroHandler();
	    apiError.setStatus(400);
	    apiError.setData(Utilitarios.formateDateTime(OffsetDateTime.now().toEpochSecond()));
	    apiError.setMessage(ex.getLocalizedMessage());
	    return new ResponseEntity<Object>(
	      apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	@Override	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<CamposErroHandler> campos = new ArrayList<>();
		ErroHandler erroHandler = new ErroHandler();
		
		for(ObjectError objectError: ex.getBindingResult().getAllErrors())
			campos.add(new CamposErroHandler(((FieldError)objectError).getField(), objectError.getDefaultMessage()));
		
		
		erroHandler.setStatus(status.value());
		erroHandler.setMessage("Um ou mais campos são invalidos!");
		erroHandler.setData(Utilitarios.formateDateTime(OffsetDateTime.now().toEpochSecond()));
		erroHandler.setCamposErro(campos);
		
		return super.handleExceptionInternal(ex, erroHandler, headers, status, request);
	}
	
	
	
	
	

	
}
