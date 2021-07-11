package AST;

import Util.position;

import java.util.List;

public class ClassDclNode extends DclNode{
    public String identifier;
    public List<OtherFuncDclNode> funcDclList;
    public List<VarDclNode> varDcList;
//    public BaseScope scope; // IR

    public ClassDclNode(position _pos, String _identifier, List<OtherFuncDclNode> _funcDclList, List<VarDclNode> _varDcList) {
        super(_pos);
        identifier = _identifier;
        funcDclList = _funcDclList;
        varDcList = _varDcList;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
