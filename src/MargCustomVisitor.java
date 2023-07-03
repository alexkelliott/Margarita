package margarita;
import margarita.*;
import margarita.variables.*;
import java.util.HashMap;

public class MargCustomVisitor extends MargBaseVisitor<Variable> {

	HashMap<String, Function> functions = new HashMap<>();
	HashMap<String, Variable> outside_vars = new HashMap<String, Variable>();

	@Override
	public Variable visitBegin_program(MargParser.Begin_programContext ctx) { 

		return visitChildren(ctx);
	}

	// This method adds the function to the function list
	@Override
	public Variable visitFunction(MargParser.FunctionContext ctx) {
		functions.put(ctx.ID().getText(), new Function(ctx));
		return null;
	}

	// This method actually goes into the function and visits
	// the functions child statements
	private Variable goIntoFunction(MargParser.FunctionContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Variable visitFunction_call(MargParser.Function_callContext ctx) {
		if (functions.containsKey(ctx.ID().getText())) {
			goIntoFunction(functions.get(ctx.ID().getText()).ctx);

		} else {
			// TODO add error handler here
			System.out.println(ctx.ID().getText() + " NOT FOUND!");
		}


		return visitChildren(ctx);
	}

	@Override
	public Variable visitShoutString(MargParser.ShoutStringContext ctx) {
		String trimmed = ctx.STRING().getText().substring(1, ctx.STRING().getText().length()-1);
		System.out.println(trimmed);
		return null;
	}

	@Override
	public Variable visitShoutExp(MargParser.ShoutExpContext ctx) {
		Variable shouted_var = this.visit(ctx.exp());
		System.out.println(this.visit(ctx.exp()));
		return shouted_var;
	}

	@Override
	public Variable visitExpParenthesis(MargParser.ExpParenthesisContext ctx) {
		return this.visit(ctx.exp());
	}

	@Override
	public Variable visitSetInt(MargParser.SetIntContext ctx) {
		Variable new_var = this.visit(ctx.exp());
		outside_vars.put(ctx.ID().getText(), new_var);
		return new_var;
	}

	@Override
	public Variable visitSetFloat(MargParser.SetFloatContext ctx) {
		Variable new_var = this.visit(ctx.exp());
		outside_vars.put(ctx.ID().getText(), new_var);
		return new_var;
	}

	@Override
	public Variable visitSetBool(MargParser.SetBoolContext ctx) {
		Variable new_var = this.visit(ctx.exp());
		outside_vars.put(ctx.ID().getText(), new_var);
		return new_var;
	}

	@Override
	public Variable visitSetString(MargParser.SetStringContext ctx) {
		String trimmed = ctx.STRING().getText().substring(1, ctx.STRING().getText().length()-1);
		Variable new_var = new StringVar(trimmed);
		outside_vars.put(ctx.ID().getText(), new_var);
		return new_var;
	}

	@Override
	public Variable visitSetIP(MargParser.SetIPContext ctx) {
		Variable new_var = new IPVar(ctx.IP().getText());
		outside_vars.put(ctx.ID().getText(), new_var);
		return new_var;
	}

	@Override
	public Variable visitExpIntLit(MargParser.ExpIntLitContext ctx) {
		Variable new_var = new IntVar(Integer.parseInt(ctx.INTLIT().getText()));
		return new_var;
	}

	@Override
	public Variable visitExpFloatLit(MargParser.ExpFloatLitContext ctx) {
		Variable new_var = new FloatVar(Float.parseFloat(ctx.FLOATLIT().getText()));
		return new_var;
	}

	@Override
	public Variable visitExpBoolLit(MargParser.ExpBoolLitContext ctx) {
		Variable new_var = new BoolVar(Boolean.parseBoolean(ctx.BOOLLIT().getText()));
		return new_var;
	}		

	@Override
	public Variable visitExpID(MargParser.ExpIDContext ctx) {
		return outside_vars.get(ctx.ID().getText());
	}
}