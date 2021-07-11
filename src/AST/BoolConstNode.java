package AST;

import Util.position;

public class BoolConstNode extends ConstExprNode{
    public boolean val;

    public BoolConstNode(position _pos, boolean _val) {
        super(_pos); val = _val;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
