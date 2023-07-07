package margarita.exceptions;
import margarita.variables.Variable;

public class FunctionReturnException extends RuntimeException {

	public Variable return_var;

	public FunctionReturnException(Variable return_var) {
		this.return_var = return_var;
	}
}