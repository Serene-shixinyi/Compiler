package AST;

import Util.position;

public class BoolTypeNode extends SimpleTypeNode{
    public BoolTypeNode(position _pos) {
        super(_pos, "bool");
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
