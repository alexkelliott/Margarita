package margarita.exceptions;

public abstract class MargException extends RuntimeException {

	public MargException() {}

	public abstract String getMsg();
}