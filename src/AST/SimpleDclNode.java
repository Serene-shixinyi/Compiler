package AST;

import Util.position;

import java.util.ArrayList;
import java.util.List;

public class SimpleDclNode extends VarDclNode{ //many declarations
    public List<OneDclNode> dcls;
    public SimpleDclNode(position _pos, List<OneDclNode> _dcls) {
        super(_pos); dcls = _dcls;
    }
    public SimpleDclNode(OneDclNode dcl) {
        super(dcl.pos);
        dcls = new ArrayList<>();
        dcls.add(dcl);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
