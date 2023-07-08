package margarita.exceptions;
import org.antlr.v4.runtime.ParserRuleContext;
import margarita.variables.Type;

public class NoReturnException extends MargException {

	private String msg;

	public NoReturnException(ParserRuleContext ctx, Type expected_type) {
		super(ctx);
		msg = preamble + "No returned variable but expected a returned variable of type " + expected_type;
	}

	public String getMsg() {
		return msg;
	}
}