package AST;

import Util.position;

public class ClassMemberNode extends ExprNode{
    public ExprNode expr;
    public String identifier;

    public ClassMemberNode(position _pos, ExprNode _expr, String _identifier) {
        super(_pos);
        expr = _expr;
        identifier = _identifier;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
