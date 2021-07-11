package AST;

import IR.operand.Operand;
import Util.position;
import Util.symbol.Type;

public abstract class ExprNode extends ASTNode{

    public enum Category{
        LVALUE, RVALUE, THIS, VOIDFUNCTION
    }
    public Category category;

    public Type type;
    public Operand operand, loperand; //IR
    public ExprNode(position _pos) {
        super(_pos);
        operand = loperand = null;
    }

}
