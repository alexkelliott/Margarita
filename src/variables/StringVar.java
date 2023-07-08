package margarita.variables;
import org.antlr.v4.runtime.ParserRuleContext;
import margarita.exceptions.*;

public class StringVar extends Variable {

	public String value;
	private Type type;

	public StringVar(String value) {
		super(value);
		this.value = value;
		this.type = Type.STRING;
	}

	public Variable calc(Op op, Variable b, ParserRuleContext ctx) {
		throw new InvalidOperationException(ctx, op, this.getType(), b.getType());
	}

	public Type getType() {
		return type;
	}

	public String toString() {
		return value;
	}
}