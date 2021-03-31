package AST;

import Util.position;

public class ArrayIndexNode extends ExprNode{
    public ExprNode array;
    public ExprNode index;

    public ArrayIndexNode(position _pos, ExprNode _array, ExprNode _index) {
        super(_pos); array = _array; index = _index;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
