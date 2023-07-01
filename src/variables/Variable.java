package margarita.variables;
import margarita.*;

public abstract class Variable {

	public Object value;
	//Type type;

	public Variable(Object value) {
		this.value = value;
	}

	public abstract Type getType();

	public abstract Variable calc(char op, Variable b);
}