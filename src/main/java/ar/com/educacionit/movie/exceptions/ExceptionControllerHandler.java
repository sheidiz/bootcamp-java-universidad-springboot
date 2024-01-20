package ar.com.educacionit.movie.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerHandler {

	@ExceptionHandler()
	public ResponseEntity<?> fallaEnValidacion(
			MethodArgumentNotValidException ex) {
		//logica de control de error
		List<String> errors = ex.getBindingResult().getAllErrors()
			.stream()
			.map(x -> x.getObjectName() + " - " + x.getDefaultMessage())
			.collect(Collectors.toList());
			
		return ResponseEntity.badRequest().body(errors);
	}
	
	//implementar propio metodo con la custum exception..
	@ExceptionHandler(MyBadRequestValidException.class)
	public ResponseEntity<?> fallaEnValidacion(
			MyBadRequestValidException ex) {
		
		//logica de control de error
		
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
	
}
