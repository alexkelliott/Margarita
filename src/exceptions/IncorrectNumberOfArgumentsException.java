package margarita.exceptions;
import margarita.variables.Type;

public class IncorrectNumberOfArgumentsException extends MargException {

	private String msg;

	public IncorrectNumberOfArgumentsException(int provided, int expected) {
		msg = "Error: " + provided + " arguments provided but expected " + expected;
	}

	public String getMsg() {
		return msg;
	}
}