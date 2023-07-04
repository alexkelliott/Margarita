package margarita;
import margarita.*;
import margarita.variables.*;
import java.util.HashMap;
import java.util.List;

public class MargCustomVisitor extends MargBaseVisitor<Variable> {

	HashMap<String, Function> functions = new HashMap<>();
	Function current_function = null;
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

	@Override
	public Variable visitFunction_call(MargParser.Function_callContext ctx) {
		if (functions.containsKey(ctx.ID().getText())) {
			Function func = functions.get(ctx.ID().getText());
			func.reset_state(); // clear variables
			
			// pass in arguments
			HashMap<String, Variable> args = new HashMap<>();
			List<MargParser.ExpContext> exp_ctxs = ctx.arg_list().exp();

 			for (int i = 0; i < exp_ctxs.size(); i++) {
 				Variable new_var = this.visit(exp_ctxs.get(i));
 				String name = func.param_info.get(i).name;
 				args.put(name, new_var);
 				// System.out.println("putting " + name + " : " + new_var);
 				// TODO type validation
 			}

			current_function = func;
 			current_function.pass_args(args);
			return visitChildren(current_function.ctx); // step into function

		} else {
			// TODO add error handler here
			System.out.println(ctx.ID().getText() + " NOT FOUND!");
		}

		return null;
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
		System.out.println(shouted_var);
		return shouted_var;
	}


	@Override
	public Variable visitSetInt(MargParser.SetIntContext ctx) {
		Variable new_var = this.visit(ctx.exp());
		if (current_function != null) {
			current_function.vars.put(ctx.ID().getText(), new_var);
		} else {
			outside_vars.put(ctx.ID().getText(), new_var);
		}
		return new_var;
	}

	@Override
	public Variable visitSetFloat(MargParser.SetFloatContext ctx) {
		Variable new_var = this.visit(ctx.exp());
		if (current_function != null) {
			current_function.vars.put(ctx.ID().getText(), new_var);
		} else {
			outside_vars.put(ctx.ID().getText(), new_var);
		}
		return new_var;
	}

	@Override
	public Variable visitSetBool(MargParser.SetBoolContext ctx) {
		Variable new_var = this.visit(ctx.exp());
		if (current_function != null) {
			current_function.vars.put(ctx.ID().getText(), new_var);
		} else {
			outside_vars.put(ctx.ID().getText(), new_var);
		}
		return new_var;
	}

	@Override
	public Variable visitSetString(MargParser.SetStringContext ctx) {
		String trimmed = ctx.STRING().getText().substring(1, ctx.STRING().getText().length()-1);
		Variable new_var = new StringVar(trimmed);
		if (current_function != null) {
			current_function.vars.put(ctx.ID().getText(), new_var);
		} else {
			outside_vars.put(ctx.ID().getText(), new_var);
		}
		return new_var;
	}

	@Override
	public Variable visitSetIP(MargParser.SetIPContext ctx) {
		Variable new_var = new IPVar(ctx.exp().getText());
		if (current_function != null) {
			current_function.vars.put(ctx.ID().getText(), new_var);
		} else {
			outside_vars.put(ctx.ID().getText(), new_var);
		}
		return new_var;
	}

	@Override
	public Variable visitExpParenthesis(MargParser.ExpParenthesisContext ctx) {
		return this.visit(ctx.exp());
	}

	@Override
	public Variable visitExpDivide(MargParser.ExpDivideContext ctx) {
		Variable a = this.visit(ctx.a);
		Variable b = this.visit(ctx.b);
		return a.calc('/', b);

	}

	@Override
	public Variable visitExpMultiply(MargParser.ExpMultiplyContext ctx) {
		Variable a = this.visit(ctx.a);
		Variable b = this.visit(ctx.b);
		return a.calc('*', b);
	}

	@Override
	public Variable visitExpSubtract(MargParser.ExpSubtractContext ctx) {
		Variable a = this.visit(ctx.a);
		Variable b = this.visit(ctx.b);
		return a.calc('-', b);
	}

	@Override
	public Variable visitExpAdd(MargParser.ExpAddContext ctx) {
		Variable a = this.visit(ctx.a);
		Variable b = this.visit(ctx.b);
		return a.calc('+', b);
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
		if (current_function != null) {
			return current_function.vars.get(ctx.ID().getText());
		} else {
			return outside_vars.get(ctx.ID().getText());
		}
	}
}