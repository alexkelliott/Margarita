package margarita.exceptions;
import margarita.variables.Variable;

/*  Note that this Exception does NOT extend MargException.
 *  This "Exception" is thrown when a function returns.
 *  It is not meant to represent an "error", but by throwing
 *  an exception, it allows us to transfer control flow back
 *  to the function call visitor without continuing to parse
 *  through the rest of the function.
 */

public class FunctionReturnException extends RuntimeException {

	public Variable return_var;

	public FunctionReturnException(Variable return_var) {
		this.return_var = return_var;
	}
}