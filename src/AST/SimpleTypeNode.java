package AST;

import Util.position;

public abstract class SimpleTypeNode extends TypeNode{
    public SimpleTypeNode(position _pos, String _typeIdentifier) {
        super(_pos, _typeIdentifier);
    }
}
