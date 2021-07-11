package AST;

import Util.position;

public class IdentifierExprNode extends ExprNode{
    public String identifier;

    public IdentifierExprNode(position _pos, String _identifier) {
        super(_pos); identifier = _identifier;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
