package margarita.exceptions;
import org.antlr.v4.runtime.ParserRuleContext;
import margarita.variables.Type;

public class InvalidReturnTypeException extends MargException {

	private String msg;

	public InvalidReturnTypeException(ParserRuleContext ctx, Type returned_type, Type expected_type) {
		super(ctx);
		msg = preamble + "Function returned variable of type " + returned_type + 
		      " but expected variable of type " + expected_type;
	}

	public String getMsg() {
		return msg;
	}
}