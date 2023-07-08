package margarita.exceptions;
import org.antlr.v4.runtime.ParserRuleContext;

public class NonBoolConditionalException extends MargException {

	private String msg;

	public NonBoolConditionalException(ParserRuleContext ctx) {
		super(ctx);
		msg = preamble + "Conditional expression does not evaluate to true or false";
	}

	public String getMsg() {
		return msg;
	}
}