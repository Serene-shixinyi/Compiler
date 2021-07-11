package AST;

import Util.position;
import java.util.List;

public class FuncCallExprNode extends ExprNode{
    public ExprNode func;
    public List<ExprNode> prmtlist;

    public FuncCallExprNode(position _pos, ExprNode _func, List<ExprNode> _prmtlist) {
        super(_pos);
        func = _func;
        prmtlist = _prmtlist;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
