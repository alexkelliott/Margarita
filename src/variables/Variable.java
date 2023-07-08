package margarita.variables;
import org.antlr.v4.runtime.ParserRuleContext;

public abstract class Variable {

	public Object value;

	public Variable(Object value) {
		this.value = value;
	}

	public abstract Type getType();

	public abstract Variable calc(Op op, Variable b, ParserRuleContext ctx);
}