package margarita.exceptions;
import org.antlr.v4.runtime.ParserRuleContext;
import margarita.variables.Type;
import margarita.variables.Op;

public class InvalidOperationException extends MargException {

	private String msg;
 
	public InvalidOperationException(ParserRuleContext ctx, Op op, Type var_type1) {
		super(ctx);
		msg = preamble + "Cannot execute " + op + " on " + var_type1;
	}

 	// Binary operations
	public InvalidOperationException(ParserRuleContext ctx, Op op, Type var_type1, Type var_type2) {
		super(ctx);
		msg = preamble + "Cannot execute " + var_type1 + " " + op + " " + var_type2;
	}

	public String getMsg() {
		return msg;
	}
}