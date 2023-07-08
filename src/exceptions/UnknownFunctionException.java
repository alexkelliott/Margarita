package margarita.exceptions;
import org.antlr.v4.runtime.ParserRuleContext;
import margarita.variables.Type;

public class UnknownFunctionException extends MargException {

	private String msg;

	public UnknownFunctionException(ParserRuleContext ctx, String unknown_func) {
		super(ctx);
		msg = preamble + "No function \"" + unknown_func + "\" has been defined";
	}

	public String getMsg() {
		return msg;
	}
}