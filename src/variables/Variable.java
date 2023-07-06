package margarita.variables;

public abstract class Variable {

	public Object value;

	public Variable(Object value) {
		this.value = value;
	}

	public abstract Type getType();

	public abstract Variable calc(Op op, Variable b);
}