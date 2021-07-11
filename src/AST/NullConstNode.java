package AST;

import Util.position;

public class NullConstNode extends ConstExprNode{

    public NullConstNode(position _pos) {
        super(_pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
