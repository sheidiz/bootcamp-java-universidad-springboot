package ar.com.educacionit.movie.exceptions;

public class MyBadRequestValidException extends RuntimeException {

	public MyBadRequestValidException(String msj) {
		super(msj);
	}
}
