package AST;

import Util.position;

public class ExprStNode extends StmtNode{
    public ExprNode expr;

    public ExprStNode(position _pos, ExprNode _expr) {
        super(_pos); expr = _expr;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
