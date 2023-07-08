package margarita;
import margarita.*;
import margarita.variables.*;
import margarita.exceptions.*;
import org.antlr.v4.runtime.tree.ParseTree;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class MargCustomVisitor extends MargBaseVisitor<Variable> {

	HashMap<String, FunctionDef> function_defs = new HashMap<>();
	Stack<FunctionCall> call_stack = new Stack<>();
	HashMap<String, Variable> outside_vars = new HashMap<String, Variable>();

	private String trim_text(String raw) {
		return raw.substring(1, raw.length() - 1);
	}

	@Override
	public Variable visitBegin_program(MargParser.Begin_programContext ctx) { 
		try {
			visitChildren(ctx);
		} catch (MargException me) {
			System.out.println(me.getMsg());
		}
		return null;
	}

	// This method adds the function to the function list.
	// Function_Call steps into the function.
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
				throw new IncorrectNumberOfArgumentsException(ctx, exp_ctxs.size(), func.param_info.size());
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
				try {
					return_var = this.visit(child); // visit child
				} catch (FunctionReturnException fre) {
					// we hit a return statement...
					// make sure that the return var is of the correct type
					if ((fre.return_var != null) && (fre.return_var.getType() != func.return_type)) {
						throw new InvalidReturnTypeException(ctx, fre.return_var.getType(), func.return_type);
					}

					call_stack.pop();
					return fre.return_var;
				}
			}

			// none of the statements we across were returns.
			// if there is an expected return, throw error
			if (func.return_type != null) {
				throw new NoReturnException(ctx, func.return_type);
			}

			call_stack.pop();
			return null;
		}

		throw new UnknownFunctionException(ctx, ctx.ID().getText());
	}

	@Override
	public Variable visitReturn(MargParser.ReturnContext ctx) {
		Variable return_var = this.visit(ctx.exp());
		throw new FunctionReturnException(return_var);
	}

	@Override
	public Variable visitShout(MargParser.ShoutContext ctx) {
		Variable shouted_var = this.visit(ctx.exp());
		System.out.println(shouted_var);
		return shouted_var;
	}

	@Override
	public Variable visitVar_set(MargParser.Var_setContext ctx) {
		Variable new_var = this.visit(ctx.exp());
		if (!call_stack.empty()) {
			call_stack.peek().vars.put(ctx.ID().getText(), new_var);
		} else {
			outside_vars.put(ctx.ID().getText(), new_var);
		}
		return null;
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
	public Variable visitConditional(MargParser.ConditionalContext ctx) {

		// if the "if" is true...
		Variable if_exp = this.visit(ctx.exp());
		if (if_exp.getType() == Type.BOOL && (Boolean)(if_exp.value)) {
			for (MargParser.Inner_statementContext inner_ctx: ctx.inner_statement()) {
				this.visit(inner_ctx);
			}
			return null;
		}

		// if any of the "else if"s are true...
		for (MargParser.Else_ifContext elif_ctx : ctx.else_if()) {
			Variable elif_exp = this.visit(elif_ctx.exp());
			if (elif_exp.getType() == Type.BOOL && (Boolean)(elif_exp.value)) {
				for (MargParser.Inner_statementContext inner_ctx: elif_ctx.inner_statement()) {
					this.visit(inner_ctx);
				}
				return null;
			}

		}

		// if nothing is true, step into the "else"...
		if (ctx.else_() != null) {
			for (MargParser.Inner_statementContext inner_ctx: ctx.else_().inner_statement()) {
				this.visit(inner_ctx);
			}
		}
		return null;
	}

	@Override
	public Variable visitExpParenthesis(MargParser.ExpParenthesisContext ctx) {
		return this.visit(ctx.exp());
	}

	@Override
	public Variable visitExpDivide(MargParser.ExpDivideContext ctx) {
		Variable a = this.visit(ctx.a);
		Variable b = this.visit(ctx.b);
		return a.calc(Op.DIV, b, ctx);

	}

	@Override
	public Variable visitExpMultiply(MargParser.ExpMultiplyContext ctx) {
		Variable a = this.visit(ctx.a);
		Variable b = this.visit(ctx.b);
		return a.calc(Op.MUL, b, ctx);
	}

	@Override
	public Variable visitExpSubtract(MargParser.ExpSubtractContext ctx) {
		Variable a = this.visit(ctx.a);
		Variable b = this.visit(ctx.b);
		return a.calc(Op.SUB, b, ctx);
	}

	@Override
	public Variable visitExpAdd(MargParser.ExpAddContext ctx) {
		Variable a = this.visit(ctx.a);
		Variable b = this.visit(ctx.b);
		return a.calc(Op.ADD, b, ctx);
	}

	@Override
	public Variable visitExpLT(MargParser.ExpLTContext ctx) {
		Variable a = this.visit(ctx.a);
		Variable b = this.visit(ctx.b);
		return a.calc(Op.LT, b, ctx);
	}

	@Override
	public Variable visitExpLE(MargParser.ExpLEContext ctx) {
		Variable a = this.visit(ctx.a);
		Variable b = this.visit(ctx.b);
		return a.calc(Op.LE, b, ctx);
	}

	@Override
	public Variable visitExpGT(MargParser.ExpGTContext ctx) {
		Variable a = this.visit(ctx.a);
		Variable b = this.visit(ctx.b);
		return a.calc(Op.GT, b, ctx);
	}

	@Override
	public Variable visitExpGE(MargParser.ExpGEContext ctx) {
		Variable a = this.visit(ctx.a);
		Variable b = this.visit(ctx.b);
		return a.calc(Op.GE, b, ctx);
	}

	@Override
	public Variable visitExpEQ(MargParser.ExpEQContext ctx) {
		Variable a = this.visit(ctx.a);
		Variable b = this.visit(ctx.b);
		return a.calc(Op.EQ, b, ctx);
	}

	@Override
	public Variable visitExpNE(MargParser.ExpNEContext ctx) {
		Variable a = this.visit(ctx.a);
		Variable b = this.visit(ctx.b);
		return a.calc(Op.NE, b, ctx);
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