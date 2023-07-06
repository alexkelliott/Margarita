package margarita.variables;

public class IntVar extends Variable {

	public int value;
	private Type type;

	public IntVar(int value) {
		super(value);
		this.value = value;
		this.type = Type.INT;
	}

	public Variable calc(Op op, Variable b) {
		
		Variable return_var = null;

		switch (b.getType()) {
			case INT:
				switch (op) {
					case DIV:
						return_var = new FloatVar((float)this.value / (Integer)b.value);
						break;
					case MUL:
						return_var = new IntVar(this.value * (Integer)b.value);
						break;
					case SUB:
						return_var = new IntVar(this.value - (Integer)b.value);
						break;
					case ADD:
						return_var = new IntVar(this.value + (Integer)b.value);
						break;
					case LT:
						return_var = new BoolVar(this.value < (Integer)b.value);
						break;
					case LE:
						return_var = new BoolVar(this.value <= (Integer)b.value);
						break;
					case GT:
						return_var = new BoolVar(this.value > (Integer)b.value);
						break;
					case GE:
						return_var = new BoolVar(this.value >= (Integer)b.value);
						break;
					case EQ:
						return_var = new BoolVar(this.value == (Integer)b.value);
						break;
					case NE:
						return_var = new BoolVar(this.value != (Integer)b.value);
						break;
				}
				break;

			case FLOAT:
				switch (op) {
					case DIV:
						return_var = new FloatVar((float)this.value / (Float)b.value);
						break;
					case MUL:
						return_var = new FloatVar((float)this.value * (Float)b.value);
						break;
					case SUB:
						return_var = new FloatVar((float)this.value - (Float)b.value);
						break;
					case ADD:
						return_var = new FloatVar((float)this.value + (Float)b.value);
						break;
					case LT:
						return_var = new BoolVar(this.value < (Float)b.value);
						break;
					case LE:
						return_var = new BoolVar(this.value <= (Float)b.value);
						break;
					case GT:
						return_var = new BoolVar(this.value > (Float)b.value);
						break;
					case GE:
						return_var = new BoolVar(this.value >= (Float)b.value);
						break;
					case EQ:
						return_var = new BoolVar(this.value == (Float)b.value);
						break;
					case NE:
						return_var = new BoolVar(this.value != (Float)b.value);
						break;
				}
				break;
		}

		return return_var;
	}

	public Type getType() {
		return type;
	}

	public String toString() {
		return Integer.toString(value);
	}
}
