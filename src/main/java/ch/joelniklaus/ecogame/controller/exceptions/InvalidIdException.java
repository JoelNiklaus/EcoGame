package ch.joelniklaus.ecogame.controller.exceptions;

public class InvalidIdException extends RuntimeException {

	private static final long serialVersionUID = -3423519805601312601L;
	
	public InvalidIdException(String s) {
		super(s);
	}
}
