package AST;

import Util.position;

public abstract class TypeNode extends ASTNode{
    public String typeIdentifier;
    public TypeNode(position _pos, String _typeIdentifier) {
        super(_pos);
        typeIdentifier = _typeIdentifier;
    }
}
