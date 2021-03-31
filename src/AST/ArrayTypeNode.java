package AST;

import Util.position;

public class ArrayTypeNode extends TypeNode{
    public TypeNode basetype;
    public int dim;
    public ArrayTypeNode(position _pos, TypeNode partype) {
        super(_pos, partype.typeIdentifier);
        if(partype instanceof ArrayTypeNode) {
            basetype = ((ArrayTypeNode)partype).basetype;
            dim = ((ArrayTypeNode)partype).dim + 1;
        }
        else {
            basetype = partype;
            dim = 1;
        }
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
