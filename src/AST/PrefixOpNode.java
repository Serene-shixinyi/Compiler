package AST;

import Util.position;

public class PrefixOpNode extends ExprNode{
    public enum Op{
        INC, DEC
    } //++ --

    public Op op;
    public ExprNode expr;
    public PrefixOpNode(position _pos, ExprNode _expr, Op _op) {
        super(_pos);
        expr = _expr; op = _op;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
