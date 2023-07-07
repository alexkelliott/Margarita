package margarita.exceptions;
import margarita.variables.Type;

public class InvalidReturnTypeException extends MargException {

	private String msg;

	public InvalidReturnTypeException(Type returned_type, Type expected_type) {
		msg = "Error: Function returned variable of type " + returned_type + 
		      " but expected variable of type " + expected_type;
	}

	public String getMsg() {
		return msg;
	}
}