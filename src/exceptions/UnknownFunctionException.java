package margarita.exceptions;
import margarita.variables.Type;

public class UnknownFunctionException extends MargException {

	private String msg;

	public UnknownFunctionException(String unknown_func) {
		msg = "Error: No function \"" + unknown_func + "\" has been defined";
	}

	public String getMsg() {
		return msg;
	}
}