package margarita;
import margarita.variables.*;
import java.util.HashMap;

public class Function {

	public MargParser.FunctionContext ctx;
	public HashMap<String, Variable> vars;

	public Function(MargParser.FunctionContext ctx) {
		this.ctx = ctx;
		this.vars = new HashMap<String, Variable>();
	}
}