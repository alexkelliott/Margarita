public class Variable {

	String name;
	Type type;

	int intVal;
	float floatVal;
	boolean boolVal;
	String strVal;
	IP ipVal;

	public Variable(String name, Type type, Object value) {
		this(type, value);
		this.name = name;
	}

	public Variable(Type type, Object value) {

		this.type = type;

		switch (type) {
			case INT:
				intVal = (int)value;
				break;
			case FLOAT:
				floatVal = (float)value;
				break;
			case BOOL:
				boolVal = (boolean)value;
				break;
			case STRING:
				strVal = value.toString();
				break;
			case IP:
				ipVal = (IP)value;
			default:
		}
	}

	public String toString() {
		String ret = "";
		switch (type) {
			case INT:
				ret = Integer.toString(intVal);
				break;
			case FLOAT:
				ret = Float.toString(floatVal);
				break;
			case BOOL:
				ret = boolVal ? "true" : "false";
				break;
			case STRING:
				ret = strVal;
				break;
			case IP:
				ret = ipVal.toString();
		}

		return ret;
	}
}