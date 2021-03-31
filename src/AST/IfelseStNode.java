package AST;

import Util.position;

public class IfelseStNode extends StmtNode{
    public ExprNode cond;
    public StmtNode stmt1;
    public StmtNode stmt2; //could be null

    public IfelseStNode(position _pos, ExprNode _cond, StmtNode _stmt1, StmtNode _stmt2) {
        super(_pos); cond = _cond; stmt1 = _stmt1; stmt2 = _stmt2;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
