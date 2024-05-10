package cl.springmachine.api.pokemon.exception;

public class CustomException extends Exception {

	private static final long serialVersionUID = 9025436188262769909L;

	public CustomException(String message) {
		super(message);
	}

}
