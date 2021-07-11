package AST;

import Util.position;

public class IntConstNode extends ConstExprNode{
    public int val;

    public IntConstNode(position _pos, int _val) {
        super(_pos); val = _val;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
