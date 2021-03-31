package AST;

import Util.position;

public class ThisExprNode extends ExprNode{
    public ThisExprNode(position _pos) {
        super(_pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}