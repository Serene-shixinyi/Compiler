package AST;

import Util.position;

public class AssignDclNode extends VarDclNode {
    public OneDclNode dcl;
    public ExprNode expr;
    public AssignDclNode(position _pos, OneDclNode _dcl, ExprNode _expr) {
        super(_pos);
        dcl = _dcl; expr = _expr;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
