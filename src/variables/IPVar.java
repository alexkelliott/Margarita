package margarita.variables;

public class IPVar extends Variable {

	private short[] octets = {0, 0, 0, 0};
	private Type type;

	public IPVar(String addr) {
		super(addr);
		this.type = Type.IP;
		String[] byte_strings = addr.split("\\.", -1);
		for (int i = 0; i < 4; i++) 
			octets[i] = Short.parseShort(byte_strings[i]);
	}

	public Variable calc(Op op, Variable b) {
		return null;
	}

	public Type getType() {
		return type;
	}

	public String toString() {
		String[] byte_strings = {"", "", "", ""};
		for (int i = 0; i < 4; i++)
			byte_strings[i] = String.valueOf(octets[i]);

		return String.join(".", byte_strings);
	}
}