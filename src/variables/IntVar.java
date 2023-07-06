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
		float new_float_val = 0;
		int new_int_val = 0;

		switch (b.getType()) {
			case INT:
				switch (op) {
					case DIV:
						new_float_val = (float)this.value / (Integer)b.value;
						break;
					case MUL:
						new_int_val = this.value * (Integer)b.value;
						break;
					case SUB:
						new_int_val = this.value - (Integer)b.value;
						break;
					case ADD:
						new_int_val = this.value + (Integer)b.value;
						break;
				}

				switch (op) {
					case DIV:
						return_var = new FloatVar(new_float_val);
						break;
					case MUL:
					case SUB:
					case ADD:
						return_var = new IntVar(new_int_val);
						break;
				}

				break;

			case FLOAT:
				switch (op) {
					case DIV:
						new_float_val = (float)this.value / (Float)b.value;
						break;
					case MUL:
						new_float_val = (float)this.value * (Float)b.value;
						break;
					case SUB:
						new_float_val = (float)this.value - (Float)b.value;
						break;
					case ADD:
						new_float_val = (float)this.value + (Float)b.value;
						break;
				}

				return_var = new FloatVar(new_float_val);
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
