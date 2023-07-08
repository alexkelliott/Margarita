package margarita.exceptions;
import org.antlr.v4.runtime.ParserRuleContext;
import margarita.ProjectMarg;

public abstract class MargException extends RuntimeException {

	protected final String preamble;

	public MargException(ParserRuleContext ctx) {
		int line_num = ctx.getStart().getLine();
		int char_pos = ctx.getStart().getCharPositionInLine();
		preamble = "ERROR: " + ProjectMarg.filename + " @ line "
			       + line_num + " char " + char_pos + ": ";
	}

	public abstract String getMsg();
}