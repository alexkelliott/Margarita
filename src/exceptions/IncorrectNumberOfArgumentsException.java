package margarita.exceptions;
import org.antlr.v4.runtime.ParserRuleContext;
import margarita.variables.Type;

public class IncorrectNumberOfArgumentsException extends MargException {

	private String msg;

	public IncorrectNumberOfArgumentsException(ParserRuleContext ctx, int provided, int expected) {
		super(ctx);
		msg = preamble + provided + " arguments provided but expected " + expected;
	}

	public String getMsg() {
		return msg;
	}
}