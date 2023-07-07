package margarita.exceptions;
import margarita.variables.Type;

public class NoReturnException extends MargException {

	private String msg;

	public NoReturnException(Type expected_type) {
		msg = "Error: No returned variable but expected a returned variable of type " + expected_type;
	}

	public String getMsg() {
		return msg;
	}
}