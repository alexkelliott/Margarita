package margarita.exceptions;
import org.antlr.v4.runtime.ParserRuleContext;

public class UndeclaredVariableUseException extends MargException {

	private String msg;

	public UndeclaredVariableUseException(ParserRuleContext ctx, String unknown_var_name) {
		super(ctx);
		msg = preamble + "No variable \"" + unknown_var_name + "\" has been defined";
	}

	public String getMsg() {
		return msg;
	}
}