package margarita.variables;
import org.antlr.v4.runtime.ParserRuleContext;
import margarita.exceptions.*;

public class BoolVar extends Variable {

	public boolean value;
	private Type type;

	public BoolVar(Boolean value) {
		super(value);
		this.value = value;
		this.type = Type.BOOL;
	}

	public Variable calc(Op op, Variable b, ParserRuleContext ctx) {
		throw new InvalidOperationException(ctx, op, this.getType(), b.getType());
	}

	public Type getType() {
		return type;
	}

	public String toString() {
		return String.valueOf(value);
	}
}