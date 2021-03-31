// Generated from C:/Users/Serene/IdeaProjects/1/src/parser\Mx.g4 by ANTLR 4.9.1
package parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MxParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MxVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MxParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(MxParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#boolConst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolConst(MxParser.BoolConstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intcst}
	 * labeled alternative in {@link MxParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntcst(MxParser.IntcstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringcst}
	 * labeled alternative in {@link MxParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringcst(MxParser.StringcstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolcst}
	 * labeled alternative in {@link MxParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolcst(MxParser.BoolcstContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nullcst}
	 * labeled alternative in {@link MxParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullcst(MxParser.NullcstContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#bracket}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBracket(MxParser.BracketContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#numbracket}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumbracket(MxParser.NumbracketContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intType}
	 * labeled alternative in {@link MxParser#simpletype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntType(MxParser.IntTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link MxParser#simpletype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolType(MxParser.BoolTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringType}
	 * labeled alternative in {@link MxParser#simpletype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringType(MxParser.StringTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identiferType}
	 * labeled alternative in {@link MxParser#simpletype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentiferType(MxParser.IdentiferTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arraytype}
	 * labeled alternative in {@link MxParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArraytype(MxParser.ArraytypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code narraytype}
	 * labeled alternative in {@link MxParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNarraytype(MxParser.NarraytypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nfunctype}
	 * labeled alternative in {@link MxParser#functype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNfunctype(MxParser.NfunctypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code voidtype}
	 * labeled alternative in {@link MxParser#functype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVoidtype(MxParser.VoidtypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#expressionlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionlist(MxParser.ExpressionlistContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newError}
	 * labeled alternative in {@link MxParser#newthings}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewError(MxParser.NewErrorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newArrayType}
	 * labeled alternative in {@link MxParser#newthings}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewArrayType(MxParser.NewArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newClassType}
	 * labeled alternative in {@link MxParser#newthings}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewClassType(MxParser.NewClassTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newSimpleType}
	 * labeled alternative in {@link MxParser#newthings}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewSimpleType(MxParser.NewSimpleTypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewExpr(MxParser.NewExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code prefixOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixOp(MxParser.PrefixOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code thisExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThisExpr(MxParser.ThisExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parentheses}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParentheses(MxParser.ParenthesesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOp(MxParser.BinaryOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classMemberExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassMemberExpr(MxParser.ClassMemberExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOp(MxParser.UnaryOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constants}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstants(MxParser.ConstantsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcCall}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncCall(MxParser.FuncCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayIndex}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayIndex(MxParser.ArrayIndexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identifierExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierExpr(MxParser.IdentifierExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code suffixOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuffixOp(MxParser.SuffixOpContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(MxParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#oneDcl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOneDcl(MxParser.OneDclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#parameterlist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterlist(MxParser.ParameterlistContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignDcl}
	 * labeled alternative in {@link MxParser#vardeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignDcl(MxParser.AssignDclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleDcl}
	 * labeled alternative in {@link MxParser#vardeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleDcl(MxParser.SimpleDclContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#funcdeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncdeclaration(MxParser.FuncdeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#constructor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructor(MxParser.ConstructorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code vardclMb}
	 * labeled alternative in {@link MxParser#classmember}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVardclMb(MxParser.VardclMbContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constructMb}
	 * labeled alternative in {@link MxParser#classmember}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructMb(MxParser.ConstructMbContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcdclMb}
	 * labeled alternative in {@link MxParser#classmember}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncdclMb(MxParser.FuncdclMbContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#classdeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassdeclaration(MxParser.ClassdeclarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varDcl}
	 * labeled alternative in {@link MxParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDcl(MxParser.VarDclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcDcl}
	 * labeled alternative in {@link MxParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDcl(MxParser.FuncDclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classDcl}
	 * labeled alternative in {@link MxParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDcl(MxParser.ClassDclContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileSt}
	 * labeled alternative in {@link MxParser#loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileSt(MxParser.WhileStContext ctx);
	/**
	 * Visit a parse tree produced by the {@code forSt}
	 * labeled alternative in {@link MxParser#loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForSt(MxParser.ForStContext ctx);
	/**
	 * Visit a parse tree produced by the {@code emptySt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptySt(MxParser.EmptyStContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockSt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockSt(MxParser.BlockStContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprSt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprSt(MxParser.ExprStContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varibalDclSt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVaribalDclSt(MxParser.VaribalDclStContext ctx);
	/**
	 * Visit a parse tree produced by the {@code returnSt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnSt(MxParser.ReturnStContext ctx);
	/**
	 * Visit a parse tree produced by the {@code breakSt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakSt(MxParser.BreakStContext ctx);
	/**
	 * Visit a parse tree produced by the {@code continueSt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueSt(MxParser.ContinueStContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifSt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfSt(MxParser.IfStContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifelseSt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfelseSt(MxParser.IfelseStContext ctx);
	/**
	 * Visit a parse tree produced by the {@code loopSt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopSt(MxParser.LoopStContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MxParser.ProgramContext ctx);
}