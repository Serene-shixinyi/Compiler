package AST;

import Util.position;

public class ClassTypeNode extends TypeNode{
    public ClassTypeNode(position _pos, String _identifier) {
        super(_pos, _identifier);
    }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
