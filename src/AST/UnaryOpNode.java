package AST;

import Util.position;

public class UnaryOpNode extends ExprNode{
    public enum Op{
        POS, NEG, NOT2, NOT
    } //+ - ! ~
    public Op op;
    public ExprNode expr;

    public UnaryOpNode(position _pos, ExprNode _expr, Op _op) {
        super(_pos);
        expr = _expr; op = _op;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
