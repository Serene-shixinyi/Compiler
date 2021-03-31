package AST;

import Util.position;

public class OneDclNode extends VarDclNode{
    public String identifier;
    public TypeNode type;
    public OneDclNode(position _pos, TypeNode _type, String _identifier) {
        super(_pos);
        type = _type;
        identifier = _identifier;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
