package AST;

import Util.position;

public class VoidTypeNode extends SimpleTypeNode{
    public VoidTypeNode(position _pos) {
        super(_pos, "void");
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
