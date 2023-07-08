package margarita;
import margarita.variables.Variable;
import java.util.HashMap;


public class FunctionCall {
	public HashMap<String, Variable> vars;
	public MargParser.Function_callContext ctx;

	public FunctionCall(MargParser.Function_callContext ctx, HashMap<String, Variable> args) {
		vars = new HashMap<>(args);
		this.ctx = ctx;
	}
}