package margarita.variables;

public class BoolVar extends Variable {

	public boolean value;
	private Type type;

	public BoolVar(Boolean value) {
		super(value);
		this.value = value;
		this.type = Type.BOOL;
	}

	public Variable calc(Op op, Variable b) {
		return null;
	}

	public Type getType() {
		return type;
	}

	public String toString() {
		return String.valueOf(value);
	}
}