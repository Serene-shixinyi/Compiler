package AST;

import Util.position;

public abstract class ConstExprNode extends ExprNode{
    public ConstExprNode(position _pos) {
        super(_pos);
    }
}