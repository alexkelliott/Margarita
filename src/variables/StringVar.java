package margarita.variables;
import margarita.*;

public class StringVar extends Variable {

	public String value;
	private Type type;

	public StringVar(String value) {
		super(value);
		this.value = value;
		this.type = Type.STRING;
	}

	public Variable calc(char op, Variable b) {
		return null;
	}

	public Type getType() {
		return type;
	}

	public String toString() {
		return value;
	}
}