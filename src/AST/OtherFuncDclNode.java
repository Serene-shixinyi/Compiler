package AST;

import Util.position;

public class OtherFuncDclNode extends FuncDclNode{
    public TypeNode type;
    public String identifier;
    public SimpleDclNode dcls;
    public boolean is_constructor;
    public OtherFuncDclNode(position _pos, BlockStNode _block, TypeNode _type, String _identifier, SimpleDclNode _dcls) {
        super(_pos, _block);
        type = _type;
        identifier = _identifier;
        dcls = _dcls;
        is_constructor = false;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
