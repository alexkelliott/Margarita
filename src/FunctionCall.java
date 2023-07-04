package margarita;
import margarita.variables.*;
import java.util.HashMap;


public class FunctionCall {
	public HashMap<String, Variable> vars;

	public FunctionCall(HashMap<String, Variable> args) {
		vars = new HashMap<>(args);
	}
}