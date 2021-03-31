package AST;

import Util.position;

public class StringTypeNode extends SimpleTypeNode{
    public StringTypeNode(position _pos) {
        super(_pos, "string");
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
