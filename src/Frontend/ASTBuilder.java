package Frontend;

import AST.*;
import Util.error.syntaxError;
import Util.position;
import org.antlr.v4.runtime.ParserRuleContext;
import parser.MxBaseVisitor;
import parser.MxParser;

import java.util.ArrayList;
import java.util.List;

public class ASTBuilder extends MxBaseVisitor<ASTNode> {
    @Override
    public ASTNode visitIdentifier(MxParser.IdentifierContext ctx) {
        return null;
    }
    @Override
    public ASTNode visitBoolConst(MxParser.BoolConstContext ctx) {
        if(ctx.toString().equals("true"))
            return new BoolConstNode(new position(ctx.getStart()), true);
        else return new BoolConstNode(new position(ctx.getStart()), false);
    }
    @Override
    public ASTNode visitIntcst(MxParser.IntcstContext ctx) {
        return new IntConstNode(new position(ctx.getStart()), Integer.parseInt(ctx.getText()));
    }
    @Override
    public ASTNode visitStringcst(MxParser.StringcstContext ctx) {
        return new StringConstNode(new position(ctx.getStart()), ctx.getText());
    }
    @Override
    public ASTNode visitBoolcst(MxParser.BoolcstContext ctx) {
        return new BoolConstNode(new position(ctx.getStart()), Boolean.parseBoolean(ctx.getText()));
    }
    @Override
    public ASTNode visitNullcst(MxParser.NullcstContext ctx) {
        return new NullConstNode(new position(ctx.getStart()));
    }
    @Override
    public ExprNode visitNumbracket(MxParser.NumbracketContext ctx) {
        return (ExprNode) visit(ctx.expression());
    }
    @Override
    public ASTNode visitIntType(MxParser.IntTypeContext ctx) {
        return new IntTypeNode(new position(ctx.getStart()));
    }
    @Override
    public ASTNode visitBoolType(MxParser.BoolTypeContext ctx) {
        return new BoolTypeNode(new position(ctx.getStart()));
    }
    @Override public ASTNode visitStringType(MxParser.StringTypeContext ctx) {
        return new StringTypeNode(new position(ctx.getStart()));
    }
    @Override public ASTNode visitIdentiferType(MxParser.IdentiferTypeContext ctx) {
        return new ClassTypeNode(new position(ctx.getStart()), ctx.getText());
    }
    @Override
    public ArrayTypeNode visitArraytype(MxParser.ArraytypeContext ctx) {
        return new ArrayTypeNode(new position(ctx.getStart()), (TypeNode)visit(ctx.type()));
    }
    @Override
    public ASTNode visitNarraytype(MxParser.NarraytypeContext ctx) {
        return visit(ctx.simpletype());
    }
    @Override
    public ASTNode visitNfunctype(MxParser.NfunctypeContext ctx) {
        return visit(ctx.type());
    }
    @Override
    public ASTNode visitVoidtype(MxParser.VoidtypeContext ctx) {
        return new VoidTypeNode(new position(ctx.getStart()));
    }
    @Override
    public ASTNode visitExpressionlist(MxParser.ExpressionlistContext ctx) { return null; }
    @Override
    public ASTNode visitNewError(MxParser.NewErrorContext ctx) {
        throw new syntaxError("Invalid new expression", new position(ctx.getStart()));
    }
    @Override
    public ASTNode visitNewArrayType(MxParser.NewArrayTypeContext ctx) {
        List<ExprNode> exprlist = new ArrayList<>();
        int num = ctx.numbracket().size(), i = 0;
        for(; i < num; ++i) {
            exprlist.add((ExprNode) visit(ctx.numbracket(i))); //numbracket:return expression
        }
        return new NewExprNode(new position(ctx.getStart()), ctx.simpletype().getText(),
                            exprlist, num + ctx.Bracket().size());
    }
    @Override
    public ASTNode visitNewClassType(MxParser.NewClassTypeContext ctx) {
        return new NewExprNode(new position(ctx.getStart()), ctx.simpletype().getText(),
                            new ArrayList<>(), 0);
    }
    @Override
    public ASTNode visitNewSimpleType(MxParser.NewSimpleTypeContext ctx) {
        return new NewExprNode(new position(ctx.getStart()), ctx.simpletype().getText(),
                new ArrayList<>(), 0);
    }
    @Override
    public ASTNode visitNewExpr(MxParser.NewExprContext ctx) {
        return visit(ctx.newthings());
    }
    @Override
    public ASTNode visitPrefixOp(MxParser.PrefixOpContext ctx) {
        if (ctx.op.getText().equals("++"))
            return new PrefixOpNode(new position(ctx.getStart()),
                        (ExprNode) visit(ctx.expression()), PrefixOpNode.Op.INC);
        else
            return new PrefixOpNode(new position(ctx.getStart()),
                        (ExprNode) visit(ctx.expression()), PrefixOpNode.Op.DEC);
    }
    @Override
    public ASTNode visitThisExpr(MxParser.ThisExprContext ctx) {
        return new ThisExprNode(new position(ctx.getStart()));
    }
    @Override
    public ASTNode visitParentheses(MxParser.ParenthesesContext ctx) {
        return visit(ctx.expression());
    }
    @Override
    public ASTNode visitBinaryOp(MxParser.BinaryOpContext ctx) {
        BinaryOpNode.Op op;
        switch (ctx.op.getText()) {
            case "*":
                op = BinaryOpNode.Op.MUL; break;
            case "/":
                op = BinaryOpNode.Op.DIV;break;
            case "%":
                op = BinaryOpNode.Op.MOD;break;
            case "+":
                op = BinaryOpNode.Op.ADD;break;
            case "-":
                op = BinaryOpNode.Op.SUB;break;
            case "<<":
                op = BinaryOpNode.Op.SHL;break;
            case ">>":
                op = BinaryOpNode.Op.SHR;break;
            case "<":
                op = BinaryOpNode.Op.LT;break;
            case ">":
                op = BinaryOpNode.Op.GT;break;
            case "<=":
                op = BinaryOpNode.Op.LEQ;break;
            case ">=":
                op = BinaryOpNode.Op.GEQ;break;
            case "==":
                op = BinaryOpNode.Op.EQ;break;
            case "!=":
                op = BinaryOpNode.Op.NEQ;break;
            case "&":
                op = BinaryOpNode.Op.AND;break;
            case "^":
                op = BinaryOpNode.Op.XOR;break;
            case "|":
                op = BinaryOpNode.Op.OR;break;
            case "&&":
                op = BinaryOpNode.Op.AND2;break;
            case "||":
                op = BinaryOpNode.Op.OR2;break;
            case "=":
                op = BinaryOpNode.Op.ASSIGN;break;
            default:
                throw new syntaxError("Binary Operation Error", new position(ctx.getStart()));
        }
        return new BinaryOpNode(new position(ctx.getStart()),
                (ExprNode) visit(ctx.expression(0)), (ExprNode) visit(ctx.expression(1)), op);
    }
    @Override
    public ASTNode visitClassMemberExpr(MxParser.ClassMemberExprContext ctx) {
        return new ClassMemberNode(new position(ctx.getStart()),
                    (ExprNode) visit(ctx.expression()), ctx.identifier().getText());
    }
    @Override
    public ASTNode visitUnaryOp(MxParser.UnaryOpContext ctx) {
        UnaryOpNode.Op op;
        switch (ctx.op.getText()) {
            case "+":
                op = UnaryOpNode.Op.POS;break;
            case "-":
                op = UnaryOpNode.Op.NEG;break;
            case "!":
                op = UnaryOpNode.Op.NOT2;break;
            case "~":
                op = UnaryOpNode.Op.NOT;break;
            default:
                throw new syntaxError("Unary Operation Error", new position(ctx.getStart()));
        }
        return new UnaryOpNode(new position(ctx.getStart()), (ExprNode) visit(ctx.expression()), op);
    }
    @Override
    public ASTNode visitSuffixOp(MxParser.SuffixOpContext ctx) {
        if (ctx.op.getText().equals("++"))
            return new SuffixOpNode(new position(ctx.getStart()),
                        (ExprNode) visit(ctx.expression()), SuffixOpNode.Op.INC);
        else
            return new SuffixOpNode(new position(ctx.getStart()),
                        (ExprNode) visit(ctx.expression()), SuffixOpNode.Op.DEC);
    }
    @Override
    public ASTNode visitConstants(MxParser.ConstantsContext ctx) {
        return visit(ctx.constant());
    }
    @Override
    public ASTNode visitFuncCall(MxParser.FuncCallContext ctx) {
        List<ExprNode> prmtlist = new ArrayList<>();
        for(ParserRuleContext expr: ctx.expressionlist().expression()) {
            prmtlist.add((ExprNode) visit(expr));
        }
        return new FuncCallExprNode(new position(ctx.getStart()),
                        (ExprNode) visit(ctx.expression()), prmtlist);
    }
    @Override
    public ASTNode visitArrayIndex(MxParser.ArrayIndexContext ctx) {
        return new ArrayIndexNode(new position(ctx.getStart()),
                    (ExprNode) visit(ctx.expression()), (ExprNode) visit(ctx.numbracket().expression()));
    }
    @Override
    public ASTNode visitIdentifierExpr(MxParser.IdentifierExprContext ctx) {
        return new IdentifierExprNode(new position(ctx.getStart()), ctx.getText());
    }
    @Override
    public ASTNode visitBlock(MxParser.BlockContext ctx) {
        List<StmtNode> stmtlist = new ArrayList<>();
        for (ParserRuleContext stmt: ctx.statement()) {
            stmtlist.add((StmtNode) visit(stmt));
        }
        return new BlockStNode(new position(ctx.getStart()), stmtlist);
    }
    @Override
    public ASTNode visitOneDcl(MxParser.OneDclContext ctx) {
        return new OneDclNode(new position(ctx.getStart()),
                (TypeNode) visit(ctx.type()), ctx.identifier().getText());
    }
    @Override
    public ASTNode visitParameterlist(MxParser.ParameterlistContext ctx) {
        List<OneDclNode> dcls = new ArrayList<>();
        for (ParserRuleContext prmt: ctx.oneDcl()) {
            dcls.add((OneDclNode) visit(prmt));
        }
        return new SimpleDclNode(new position(ctx.getStart()), dcls);
    }
    @Override
    public ASTNode visitAssignDcl(MxParser.AssignDclContext ctx) {
        return new AssignDclNode(new position(ctx.getStart()),
                (OneDclNode) visit(ctx.oneDcl()), (ExprNode) visit(ctx.expression()));
    }
    @Override
    public ASTNode visitSimpleDcl(MxParser.SimpleDclContext ctx) {
        List<OneDclNode> dcls = new ArrayList<>();
        TypeNode typenode = (TypeNode) visit(ctx.type());
        position pos = new position(ctx.getStart());
        int num = ctx.identifier().size(), i = 0;
        for (; i < num; ++i)
            dcls.add(new OneDclNode(pos, typenode, ctx.identifier(i).getText()));
        return new SimpleDclNode(pos, dcls);
    }

    @Override
    public ASTNode visitFuncdeclaration(MxParser.FuncdeclarationContext ctx) {
        SimpleDclNode dcls = (SimpleDclNode) visit(ctx.parameterlist());
        return new OtherFuncDclNode(new position(ctx.getStart()), (BlockStNode) visit(ctx.block()),
                    (TypeNode) visit(ctx.functype()), ctx.identifier().getText(), dcls);
    }
    public ASTNode solve(MxParser.FuncdeclarationContext ctx) {
        SimpleDclNode dcls = (SimpleDclNode) visit(ctx.parameterlist());
        if(ctx.functype().getText().equals("int") && ctx.identifier().getText().equals("main")
            && dcls.dcls.size() == 0)
            return new MainFuncDclNode(new position(ctx.getStart()), (BlockStNode) visit(ctx.block()));
        else {
            return new OtherFuncDclNode(new position(ctx.getStart()), (BlockStNode) visit(ctx.block()),
                    (TypeNode) visit(ctx.functype()), ctx.identifier().getText(), dcls);
        }
    }
    @Override
    public ASTNode visitConstructor(MxParser.ConstructorContext ctx) {
        return new OtherFuncDclNode(new position(ctx.getStart()), (BlockStNode) visit(ctx.block()),
                null, //let it be void
                ctx.identifier().getText(), (SimpleDclNode) visit(ctx.parameterlist()));
    }
    @Override
    public ASTNode visitVardclMb(MxParser.VardclMbContext ctx) {
        return visit(ctx.vardeclaration());
    }
    @Override
    public ASTNode visitConstructMb(MxParser.ConstructMbContext ctx) {
        return visit(ctx.constructor());
    }
    @Override
    public ASTNode visitFuncdclMb(MxParser.FuncdclMbContext ctx) {
        return visit(ctx.funcdeclaration());
    }
    @Override
    public ASTNode visitClassdeclaration(MxParser.ClassdeclarationContext ctx) {
        String identifier = ctx.identifier().getText();
        List<OtherFuncDclNode> funclist = new ArrayList<>();
        List<VarDclNode> varlist = new ArrayList<>();
        for(MxParser.ClassmemberContext mb: ctx.classmember()) {
            ASTNode now = visit(mb);
            if(now instanceof FuncDclNode) funclist.add((OtherFuncDclNode) now);
            else if(now instanceof VarDclNode) varlist.add((VarDclNode) now);
            else throw new syntaxError("Class declaration", new position(ctx.getStart()));
        }
        return new ClassDclNode(new position(ctx.getStart()), identifier, funclist, varlist);
    }
    @Override
    public ASTNode visitVarDcl(MxParser.VarDclContext ctx) {
        return visit(ctx.vardeclaration());
    }
    @Override
    public ASTNode visitFuncDcl(MxParser.FuncDclContext ctx) {
        return solve(ctx.funcdeclaration());
    }
    @Override
    public ASTNode visitClassDcl(MxParser.ClassDclContext ctx) {
        return visit(ctx.classdeclaration());
    }
    @Override
    public ASTNode visitWhileSt(MxParser.WhileStContext ctx) {
        return new WhileStNode(new position(ctx.getStart()),
                (ExprNode) visit(ctx.cond), (StmtNode) visit(ctx.statement()));
    }
    @Override
    public ASTNode visitForSt(MxParser.ForStContext ctx) {
        ExprNode init = ctx.init == null? null : (ExprNode) visit(ctx.init);
        ExprNode cond = ctx.cond == null? null : (ExprNode) visit(ctx.cond);
        ExprNode incr = ctx.incr == null? null : (ExprNode) visit(ctx.incr);
        return new ForStNode(new position(ctx.getStart()),
                init, cond, incr, (StmtNode) visit(ctx.statement()));
    }
    @Override
    public ASTNode visitEmptySt(MxParser.EmptyStContext ctx) { return null; }
    @Override
    public ASTNode visitBlockSt(MxParser.BlockStContext ctx) {
        return visit(ctx.block());
    }
    @Override
    public ASTNode visitExprSt(MxParser.ExprStContext ctx) {
        return new ExprStNode(new position(ctx.getStart()), (ExprNode) visit(ctx.expression()));
    }
    @Override
    public ASTNode visitVaribalDclSt(MxParser.VaribalDclStContext ctx) {
        return visit(ctx.vardeclaration());
    }
    @Override
    public ASTNode visitReturnSt(MxParser.ReturnStContext ctx) {
        ExprNode expr = ctx.expression() == null? null : (ExprNode) visit(ctx.expression());
        return new ReturnNode(new position(ctx.getStart()), expr);
    }
    @Override
    public ASTNode visitBreakSt(MxParser.BreakStContext ctx) {
        return new BreakNode(new position(ctx.getStart()));
    }
    @Override
    public ASTNode visitContinueSt(MxParser.ContinueStContext ctx) {
        return new ContinueNode(new position(ctx.getStart()));
    }
    @Override
    public ASTNode visitIfSt(MxParser.IfStContext ctx) {
        return new IfelseStNode(new position(ctx.getStart()), (ExprNode) visit(ctx.cond),
                (StmtNode) visit(ctx.statement()), null);
    }
    @Override
    public ASTNode visitIfelseSt(MxParser.IfelseStContext ctx) {
        return new IfelseStNode(new position(ctx.getStart()), (ExprNode) visit(ctx.cond),
                (StmtNode) visit(ctx.stmt1), (StmtNode) visit(ctx.stmt2));
    }
    @Override
    public ASTNode visitLoopSt(MxParser.LoopStContext ctx) {
        return visit(ctx.loop());
    }
    @Override
    public ASTNode visitProgram(MxParser.ProgramContext ctx) {
        List<DclNode> dcls = new ArrayList<>();
        for(ParserRuleContext now: ctx.declaration()) {
            dcls.add((DclNode) visit(now));
        }
        return new ProgramNode(new position(ctx.getStart()), dcls);
    }
}
