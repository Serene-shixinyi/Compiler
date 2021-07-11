package AST;

import Util.position;

import java.util.ArrayList;
import java.util.List;

public class NewExprNode extends ExprNode{
    public String identifier;
    public int dimnum;

    public List<ExprNode> exprlist = new ArrayList<ExprNode>();
    public NewExprNode(position _pos, String _identifier, List<ExprNode> _exprlist, int _dimnum) {
        super(_pos);
        identifier = _identifier;
        exprlist = _exprlist;
        dimnum = _dimnum;
    }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
