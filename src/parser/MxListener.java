// Generated from C:/Users/Serene/IdeaProjects/1/src/parser\Mx.g4 by ANTLR 4.9.1
package parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MxParser}.
 */
public interface MxListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MxParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(MxParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(MxParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#boolConst}.
	 * @param ctx the parse tree
	 */
	void enterBoolConst(MxParser.BoolConstContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#boolConst}.
	 * @param ctx the parse tree
	 */
	void exitBoolConst(MxParser.BoolConstContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intcst}
	 * labeled alternative in {@link MxParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterIntcst(MxParser.IntcstContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intcst}
	 * labeled alternative in {@link MxParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitIntcst(MxParser.IntcstContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringcst}
	 * labeled alternative in {@link MxParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterStringcst(MxParser.StringcstContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringcst}
	 * labeled alternative in {@link MxParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitStringcst(MxParser.StringcstContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolcst}
	 * labeled alternative in {@link MxParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterBoolcst(MxParser.BoolcstContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolcst}
	 * labeled alternative in {@link MxParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitBoolcst(MxParser.BoolcstContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nullcst}
	 * labeled alternative in {@link MxParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterNullcst(MxParser.NullcstContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nullcst}
	 * labeled alternative in {@link MxParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitNullcst(MxParser.NullcstContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#bracket}.
	 * @param ctx the parse tree
	 */
	void enterBracket(MxParser.BracketContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#bracket}.
	 * @param ctx the parse tree
	 */
	void exitBracket(MxParser.BracketContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#numbracket}.
	 * @param ctx the parse tree
	 */
	void enterNumbracket(MxParser.NumbracketContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#numbracket}.
	 * @param ctx the parse tree
	 */
	void exitNumbracket(MxParser.NumbracketContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intType}
	 * labeled alternative in {@link MxParser#simpletype}.
	 * @param ctx the parse tree
	 */
	void enterIntType(MxParser.IntTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intType}
	 * labeled alternative in {@link MxParser#simpletype}.
	 * @param ctx the parse tree
	 */
	void exitIntType(MxParser.IntTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link MxParser#simpletype}.
	 * @param ctx the parse tree
	 */
	void enterBoolType(MxParser.BoolTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolType}
	 * labeled alternative in {@link MxParser#simpletype}.
	 * @param ctx the parse tree
	 */
	void exitBoolType(MxParser.BoolTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringType}
	 * labeled alternative in {@link MxParser#simpletype}.
	 * @param ctx the parse tree
	 */
	void enterStringType(MxParser.StringTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringType}
	 * labeled alternative in {@link MxParser#simpletype}.
	 * @param ctx the parse tree
	 */
	void exitStringType(MxParser.StringTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identiferType}
	 * labeled alternative in {@link MxParser#simpletype}.
	 * @param ctx the parse tree
	 */
	void enterIdentiferType(MxParser.IdentiferTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identiferType}
	 * labeled alternative in {@link MxParser#simpletype}.
	 * @param ctx the parse tree
	 */
	void exitIdentiferType(MxParser.IdentiferTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arraytype}
	 * labeled alternative in {@link MxParser#type}.
	 * @param ctx the parse tree
	 */
	void enterArraytype(MxParser.ArraytypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arraytype}
	 * labeled alternative in {@link MxParser#type}.
	 * @param ctx the parse tree
	 */
	void exitArraytype(MxParser.ArraytypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code narraytype}
	 * labeled alternative in {@link MxParser#type}.
	 * @param ctx the parse tree
	 */
	void enterNarraytype(MxParser.NarraytypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code narraytype}
	 * labeled alternative in {@link MxParser#type}.
	 * @param ctx the parse tree
	 */
	void exitNarraytype(MxParser.NarraytypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nfunctype}
	 * labeled alternative in {@link MxParser#functype}.
	 * @param ctx the parse tree
	 */
	void enterNfunctype(MxParser.NfunctypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nfunctype}
	 * labeled alternative in {@link MxParser#functype}.
	 * @param ctx the parse tree
	 */
	void exitNfunctype(MxParser.NfunctypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code voidtype}
	 * labeled alternative in {@link MxParser#functype}.
	 * @param ctx the parse tree
	 */
	void enterVoidtype(MxParser.VoidtypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code voidtype}
	 * labeled alternative in {@link MxParser#functype}.
	 * @param ctx the parse tree
	 */
	void exitVoidtype(MxParser.VoidtypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#expressionlist}.
	 * @param ctx the parse tree
	 */
	void enterExpressionlist(MxParser.ExpressionlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#expressionlist}.
	 * @param ctx the parse tree
	 */
	void exitExpressionlist(MxParser.ExpressionlistContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newError}
	 * labeled alternative in {@link MxParser#newthings}.
	 * @param ctx the parse tree
	 */
	void enterNewError(MxParser.NewErrorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newError}
	 * labeled alternative in {@link MxParser#newthings}.
	 * @param ctx the parse tree
	 */
	void exitNewError(MxParser.NewErrorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newArrayType}
	 * labeled alternative in {@link MxParser#newthings}.
	 * @param ctx the parse tree
	 */
	void enterNewArrayType(MxParser.NewArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newArrayType}
	 * labeled alternative in {@link MxParser#newthings}.
	 * @param ctx the parse tree
	 */
	void exitNewArrayType(MxParser.NewArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newClassType}
	 * labeled alternative in {@link MxParser#newthings}.
	 * @param ctx the parse tree
	 */
	void enterNewClassType(MxParser.NewClassTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newClassType}
	 * labeled alternative in {@link MxParser#newthings}.
	 * @param ctx the parse tree
	 */
	void exitNewClassType(MxParser.NewClassTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newSimpleType}
	 * labeled alternative in {@link MxParser#newthings}.
	 * @param ctx the parse tree
	 */
	void enterNewSimpleType(MxParser.NewSimpleTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newSimpleType}
	 * labeled alternative in {@link MxParser#newthings}.
	 * @param ctx the parse tree
	 */
	void exitNewSimpleType(MxParser.NewSimpleTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNewExpr(MxParser.NewExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNewExpr(MxParser.NewExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code prefixOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPrefixOp(MxParser.PrefixOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code prefixOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPrefixOp(MxParser.PrefixOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code thisExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterThisExpr(MxParser.ThisExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code thisExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitThisExpr(MxParser.ThisExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parentheses}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParentheses(MxParser.ParenthesesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parentheses}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParentheses(MxParser.ParenthesesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryOp(MxParser.BinaryOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryOp(MxParser.BinaryOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code classMemberExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterClassMemberExpr(MxParser.ClassMemberExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code classMemberExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitClassMemberExpr(MxParser.ClassMemberExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOp(MxParser.UnaryOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOp(MxParser.UnaryOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code constants}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterConstants(MxParser.ConstantsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code constants}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitConstants(MxParser.ConstantsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funcCall}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFuncCall(MxParser.FuncCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funcCall}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFuncCall(MxParser.FuncCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayIndex}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArrayIndex(MxParser.ArrayIndexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayIndex}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArrayIndex(MxParser.ArrayIndexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identifierExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdentifierExpr(MxParser.IdentifierExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identifierExpr}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdentifierExpr(MxParser.IdentifierExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code suffixOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSuffixOp(MxParser.SuffixOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code suffixOp}
	 * labeled alternative in {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSuffixOp(MxParser.SuffixOpContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(MxParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(MxParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#oneDcl}.
	 * @param ctx the parse tree
	 */
	void enterOneDcl(MxParser.OneDclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#oneDcl}.
	 * @param ctx the parse tree
	 */
	void exitOneDcl(MxParser.OneDclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#parameterlist}.
	 * @param ctx the parse tree
	 */
	void enterParameterlist(MxParser.ParameterlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#parameterlist}.
	 * @param ctx the parse tree
	 */
	void exitParameterlist(MxParser.ParameterlistContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignDcl}
	 * labeled alternative in {@link MxParser#vardeclaration}.
	 * @param ctx the parse tree
	 */
	void enterAssignDcl(MxParser.AssignDclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignDcl}
	 * labeled alternative in {@link MxParser#vardeclaration}.
	 * @param ctx the parse tree
	 */
	void exitAssignDcl(MxParser.AssignDclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleDcl}
	 * labeled alternative in {@link MxParser#vardeclaration}.
	 * @param ctx the parse tree
	 */
	void enterSimpleDcl(MxParser.SimpleDclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleDcl}
	 * labeled alternative in {@link MxParser#vardeclaration}.
	 * @param ctx the parse tree
	 */
	void exitSimpleDcl(MxParser.SimpleDclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#funcdeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFuncdeclaration(MxParser.FuncdeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#funcdeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFuncdeclaration(MxParser.FuncdeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#constructor}.
	 * @param ctx the parse tree
	 */
	void enterConstructor(MxParser.ConstructorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#constructor}.
	 * @param ctx the parse tree
	 */
	void exitConstructor(MxParser.ConstructorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code vardclMb}
	 * labeled alternative in {@link MxParser#classmember}.
	 * @param ctx the parse tree
	 */
	void enterVardclMb(MxParser.VardclMbContext ctx);
	/**
	 * Exit a parse tree produced by the {@code vardclMb}
	 * labeled alternative in {@link MxParser#classmember}.
	 * @param ctx the parse tree
	 */
	void exitVardclMb(MxParser.VardclMbContext ctx);
	/**
	 * Enter a parse tree produced by the {@code constructMb}
	 * labeled alternative in {@link MxParser#classmember}.
	 * @param ctx the parse tree
	 */
	void enterConstructMb(MxParser.ConstructMbContext ctx);
	/**
	 * Exit a parse tree produced by the {@code constructMb}
	 * labeled alternative in {@link MxParser#classmember}.
	 * @param ctx the parse tree
	 */
	void exitConstructMb(MxParser.ConstructMbContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funcdclMb}
	 * labeled alternative in {@link MxParser#classmember}.
	 * @param ctx the parse tree
	 */
	void enterFuncdclMb(MxParser.FuncdclMbContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funcdclMb}
	 * labeled alternative in {@link MxParser#classmember}.
	 * @param ctx the parse tree
	 */
	void exitFuncdclMb(MxParser.FuncdclMbContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#classdeclaration}.
	 * @param ctx the parse tree
	 */
	void enterClassdeclaration(MxParser.ClassdeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#classdeclaration}.
	 * @param ctx the parse tree
	 */
	void exitClassdeclaration(MxParser.ClassdeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varDcl}
	 * labeled alternative in {@link MxParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDcl(MxParser.VarDclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varDcl}
	 * labeled alternative in {@link MxParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDcl(MxParser.VarDclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funcDcl}
	 * labeled alternative in {@link MxParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterFuncDcl(MxParser.FuncDclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funcDcl}
	 * labeled alternative in {@link MxParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitFuncDcl(MxParser.FuncDclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code classDcl}
	 * labeled alternative in {@link MxParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterClassDcl(MxParser.ClassDclContext ctx);
	/**
	 * Exit a parse tree produced by the {@code classDcl}
	 * labeled alternative in {@link MxParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitClassDcl(MxParser.ClassDclContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileSt}
	 * labeled alternative in {@link MxParser#loop}.
	 * @param ctx the parse tree
	 */
	void enterWhileSt(MxParser.WhileStContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileSt}
	 * labeled alternative in {@link MxParser#loop}.
	 * @param ctx the parse tree
	 */
	void exitWhileSt(MxParser.WhileStContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forSt}
	 * labeled alternative in {@link MxParser#loop}.
	 * @param ctx the parse tree
	 */
	void enterForSt(MxParser.ForStContext ctx);
	/**
	 * Exit a parse tree produced by the {@code forSt}
	 * labeled alternative in {@link MxParser#loop}.
	 * @param ctx the parse tree
	 */
	void exitForSt(MxParser.ForStContext ctx);
	/**
	 * Enter a parse tree produced by the {@code emptySt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterEmptySt(MxParser.EmptyStContext ctx);
	/**
	 * Exit a parse tree produced by the {@code emptySt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitEmptySt(MxParser.EmptyStContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blockSt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBlockSt(MxParser.BlockStContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blockSt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBlockSt(MxParser.BlockStContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprSt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterExprSt(MxParser.ExprStContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprSt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitExprSt(MxParser.ExprStContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varibalDclSt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterVaribalDclSt(MxParser.VaribalDclStContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varibalDclSt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitVaribalDclSt(MxParser.VaribalDclStContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnSt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReturnSt(MxParser.ReturnStContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnSt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReturnSt(MxParser.ReturnStContext ctx);
	/**
	 * Enter a parse tree produced by the {@code breakSt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBreakSt(MxParser.BreakStContext ctx);
	/**
	 * Exit a parse tree produced by the {@code breakSt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBreakSt(MxParser.BreakStContext ctx);
	/**
	 * Enter a parse tree produced by the {@code continueSt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterContinueSt(MxParser.ContinueStContext ctx);
	/**
	 * Exit a parse tree produced by the {@code continueSt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitContinueSt(MxParser.ContinueStContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifSt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfSt(MxParser.IfStContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifSt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfSt(MxParser.IfStContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifelseSt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfelseSt(MxParser.IfelseStContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifelseSt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfelseSt(MxParser.IfelseStContext ctx);
	/**
	 * Enter a parse tree produced by the {@code loopSt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterLoopSt(MxParser.LoopStContext ctx);
	/**
	 * Exit a parse tree produced by the {@code loopSt}
	 * labeled alternative in {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitLoopSt(MxParser.LoopStContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MxParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MxParser.ProgramContext ctx);
}