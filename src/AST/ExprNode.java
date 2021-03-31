package AST;

import Util.position;
import Util.symbol.Type;

public abstract class ExprNode extends ASTNode{

    public enum Category{
        LVALUE, RVALUE, THIS, VOIDFUNCTION
    }
    public Category category;

    public Type type;
    public ExprNode(position _pos) {
        super(_pos);
    }

}
