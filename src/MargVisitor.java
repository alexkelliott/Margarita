// Generated from Marg.g4 by ANTLR 4.13.0
package margarita;

import margarita.*;
import margarita.variables.*;

import java.util.HashMap;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MargParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MargVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MargParser#begin_program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBegin_program(MargParser.Begin_programContext ctx);
	/**
	 * Visit a parse tree produced by {@link MargParser#outer_statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOuter_statements(MargParser.Outer_statementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MargParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(MargParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MargParser#function_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_call(MargParser.Function_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link MargParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(MargParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MargParser#var_statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_statement(MargParser.Var_statementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SetInt}
	 * labeled alternative in {@link MargParser#var_set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetInt(MargParser.SetIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SetFloat}
	 * labeled alternative in {@link MargParser#var_set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetFloat(MargParser.SetFloatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SetBool}
	 * labeled alternative in {@link MargParser#var_set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetBool(MargParser.SetBoolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SetString}
	 * labeled alternative in {@link MargParser#var_set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetString(MargParser.SetStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SetIP}
	 * labeled alternative in {@link MargParser#var_set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetIP(MargParser.SetIPContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ShoutString}
	 * labeled alternative in {@link MargParser#shout}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShoutString(MargParser.ShoutStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ShoutExp}
	 * labeled alternative in {@link MargParser#shout}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShoutExp(MargParser.ShoutExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpIP}
	 * labeled alternative in {@link MargParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpIP(MargParser.ExpIPContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpFloatLit}
	 * labeled alternative in {@link MargParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpFloatLit(MargParser.ExpFloatLitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpMultiply}
	 * labeled alternative in {@link MargParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpMultiply(MargParser.ExpMultiplyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpParenthesis}
	 * labeled alternative in {@link MargParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpParenthesis(MargParser.ExpParenthesisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpBoolLit}
	 * labeled alternative in {@link MargParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpBoolLit(MargParser.ExpBoolLitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpDivide}
	 * labeled alternative in {@link MargParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpDivide(MargParser.ExpDivideContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpFunctionCall}
	 * labeled alternative in {@link MargParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpFunctionCall(MargParser.ExpFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpAdd}
	 * labeled alternative in {@link MargParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpAdd(MargParser.ExpAddContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpIntLit}
	 * labeled alternative in {@link MargParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpIntLit(MargParser.ExpIntLitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpID}
	 * labeled alternative in {@link MargParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpID(MargParser.ExpIDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpSubtract}
	 * labeled alternative in {@link MargParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpSubtract(MargParser.ExpSubtractContext ctx);
}