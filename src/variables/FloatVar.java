package margarita.variables;
import org.antlr.v4.runtime.ParserRuleContext;
import margarita.exceptions.*;

public class FloatVar extends Variable {

	public float value;
	private Type type;

	public FloatVar(float value) {
		super(value);
		this.value = value;
		this.type = Type.FLOAT;
	}

	public Variable calc(Op op, Variable b, ParserRuleContext ctx) {
		Variable return_var = null;
		float new_float_val = 0;
		float b_val;

		switch (b.getType()) {
			case INT:
				b_val = ((Integer)b.value).floatValue();
				break;
			case FLOAT:
				b_val = (Float)b.value;
				break;
			default:
				throw new InvalidOperationException(ctx, op, this.getType(), b.getType());
		}
		
		switch (op) {
			case DIV:
				return_var = new FloatVar(this.value / b_val);
				break;
			case MUL:
				return_var = new FloatVar(this.value * b_val);
				break;
			case SUB:
				return_var = new FloatVar(this.value - b_val);
				break;
			case ADD:
				return_var = new FloatVar(this.value + b_val);
				break;
			case LT:
				return_var = new BoolVar(this.value < b_val);
				break;
			case LE:
				return_var = new BoolVar(this.value <= b_val);
				break;
			case GT:
				return_var = new BoolVar(this.value > b_val);
				break;
			case GE:
				return_var = new BoolVar(this.value >= b_val);
				break;
			case EQ:
				return_var = new BoolVar(this.value == b_val);
				break;
			case NE:
				return_var = new BoolVar(this.value != b_val);
				break;
		}
		
		return return_var;
	}
	public Type getType() {
		return type;
	}

	public String toString() {
		return Float.toString(value);
	}
}