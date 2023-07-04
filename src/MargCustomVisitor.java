package margarita;
import margarita.*;
import margarita.variables.*;
import org.antlr.v4.runtime.tree.ParseTree;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class MargCustomVisitor extends MargBaseVisitor<Variable> {

	HashMap<String, FunctionDef> function_defs = new HashMap<>();
	FunctionDef current_function = null; // TODO: remove
	Stack<FunctionCall> call_stack = new Stack<>();
	HashMap<String, Variable> outside_vars = new HashMap<String, Variable>();

	private String trim_text(String raw) {
		return raw.substring(1, raw.length() - 1);
	}

	@Override
	public Variable visitBegin_program(MargParser.Begin_programContext ctx) { 

		return visitChildren(ctx);
	}

	// This method adds the function to the function list
	@Override
	public Variable visitFunction(MargParser.FunctionContext ctx) {
		function_defs.put(ctx.ID().getText(), new FunctionDef(ctx));
		return null;
	}

	@Override
	public Variable visitFunction_call(MargParser.Function_callContext ctx) {
		if (function_defs.containsKey(ctx.ID().getText())) {
			FunctionDef func = function_defs.get(ctx.ID().getText());
			
			HashMap<String, Variable> args = new HashMap<>();
			List<MargParser.ExpContext> exp_ctxs = ctx.arg_list().exp();

			// validate correct number of args are passed in
			if (exp_ctxs.size() != func.param_info.size()) {
				// TODO: add error handler here
				System.out.println("Error: " + func.param_info.size() +
					" arguments expected but " + exp_ctxs.size() + " provided.");
			}

			// create argument list
 			for (int i = 0; i < exp_ctxs.size(); i++) {
 				Variable new_var = this.visit(exp_ctxs.get(i));
 				String name = func.param_info.get(i).name;

 				if (new_var.getType() == func.param_info.get(i).type) {
 					args.put(name, new_var);
 				}
 			}

 			call_stack.push(new FunctionCall(args));

			// step into function
			Variable return_var = null;
			for (ParseTree child : func.ctx.children) {
				return_var = this.visit(child); // visit child
				// if we hit a return statement, return the var
				if (return_var != null) {

					// make sure that the return var is of the correct type
					if (return_var.getType() != func.return_type) {
						// TODO: add error handler here
						System.out.println("Error: returned type " +
							return_var.getType() + " but expected type " + func.return_type);
					}

					call_stack.pop();
					return return_var;
				}

			}

			// none of the statements we across were returns.
			// if there is an expected return, throw error
			if (func.return_type != null) {
				System.out.println("Error: no returned variable but expected type " + func.return_type);
			}

			call_stack.pop();
			return null;
		}

		// TODO: add error handler here for unknown function
		System.out.println("Error: No function " + ctx.ID().getText() + " has been defined.");

		return null;
	}

	@Override
	public Variable visitReturn(MargParser.ReturnContext ctx) {
		Variable return_var = this.visit(ctx.exp());
		return return_var;
	}

	@Override
	public Variable visitInner_statement(MargParser.Inner_statementContext ctx) {
		Variable return_var = null;
		for (ParseTree child : ctx.children) {
			return_var = this.visit(child);

			// if we find a return, return that variable to Function_callContext
			if (child instanceof MargParser.ReturnContext) {
				return return_var;
			}
		}

		return null;
	}

	@Override
	public Variable visitShout(MargParser.ShoutContext ctx) {
		Variable shouted_var = this.visit(ctx.exp());
		System.out.println(shouted_var);
		return shouted_var;
	}


	@Override
	public Variable visitSetInt(MargParser.SetIntContext ctx) {
		Variable new_var = this.visit(ctx.exp());
		if (!call_stack.empty()) {
			call_stack.peek().vars.put(ctx.ID().getText(), new_var);
		} else {
			outside_vars.put(ctx.ID().getText(), new_var);
		}
		return new_var;
	}

	@Override
	public Variable visitSetFloat(MargParser.SetFloatContext ctx) {
		Variable new_var = this.visit(ctx.exp());
		if (!call_stack.empty()) {
			call_stack.peek().vars.put(ctx.ID().getText(), new_var);
		} else {
			outside_vars.put(ctx.ID().getText(), new_var);
		}
		return new_var;
	}

	@Override
	public Variable visitSetBool(MargParser.SetBoolContext ctx) {
		Variable new_var = this.visit(ctx.exp());
		if (!call_stack.empty()) {
			call_stack.peek().vars.put(ctx.ID().getText(), new_var);
		} else {
			outside_vars.put(ctx.ID().getText(), new_var);
		}
		return new_var;
	}

	@Override
	public Variable visitSetString(MargParser.SetStringContext ctx) {
		Variable new_var = this.visit(ctx.exp());
		if (!call_stack.empty()) {
			call_stack.peek().vars.put(ctx.ID().getText(), new_var);
		} else {
			outside_vars.put(ctx.ID().getText(), new_var);
		}
		return new_var;
	}

	@Override
	public Variable visitSetIP(MargParser.SetIPContext ctx) {
		Variable new_var = this.visit(ctx.exp());
		if (!call_stack.empty()) {
			call_stack.peek().vars.put(ctx.ID().getText(), new_var);
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
	public Variable visitExpString(MargParser.ExpStringContext ctx) {
		String trimmed = this.trim_text(ctx.STRING().getText());
		Variable new_var = new StringVar(trimmed);
		return new_var;
	}

	@Override
	public Variable visitExpIP(MargParser.ExpIPContext ctx) {
		Variable new_var = new IPVar(ctx.IP().getText());
		return new_var;
	}

	@Override
	public Variable visitExpID(MargParser.ExpIDContext ctx) {
		if (!call_stack.empty()) {
			return call_stack.peek().vars.get(ctx.ID().getText());
		} else {
			return outside_vars.get(ctx.ID().getText());
		}
	}
}