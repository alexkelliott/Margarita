public class FloatVar extends Variable {

	public float value;
	private Type type;

	public FloatVar(float value) {
		super(value);
		this.value = value;
		this.type = Type.FLOAT;
	}

	public Variable calc(char op, Variable b) {
		Variable return_var = null;
		float new_float_val = 0;
		float b_val = 0;

		switch (b.getType()) {
			case INT:
				b_val = ((Integer)b.value).floatValue();
				break;
			case FLOAT:
				b_val = (Float)b.value;
				break;
		}
		
		switch (op) {
			case '/':
				new_float_val = this.value / b_val;
				break;
			case '*':
				new_float_val = this.value * b_val;
				break;
			case '-':
				new_float_val = this.value - b_val;
				break;
			case '+':
				new_float_val = this.value + b_val;
				break;
		}
		
		return new FloatVar(new_float_val);
	}
	public Type getType() {
		return type;
	}

	public String toString() {
		return Float.toString(value);
	}
}