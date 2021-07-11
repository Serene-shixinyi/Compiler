package AST;

import Util.position;

public class ForStNode extends StmtNode{
    public ExprNode init;
    public ExprNode cond;
    public ExprNode incr;
    public StmtNode stmt;

    public ForStNode(position _pos, ExprNode _init, ExprNode _cond, ExprNode _incr, StmtNode _stmt) {
        super(_pos);
        init = _init; cond = _cond; incr = _incr; stmt = _stmt;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

}
