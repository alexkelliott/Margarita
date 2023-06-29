public class IP {
	
	byte[] octets = {0, 0, 0, 0};

	public IP(String addr) {
		String[] byte_strings = addr.split("\\.", -1);
		for (int i = 0; i < 4; i++)
			octets[i] = Byte.parseByte(byte_strings[i]);
	}

	public String toString() {
		String[] byte_strings = {"", "", "", ""};
		for (int i = 0; i < 4; i++)
			byte_strings[i] = String.valueOf(octets[i]);

		return String.join(".", byte_strings);
	}
}