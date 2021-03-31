package AST;

import Util.position;

public class StringConstNode extends ConstExprNode{
    public String val;

    public StringConstNode(position _pos, String _val) {
        super(_pos); val = _val;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
