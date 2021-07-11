package AST;

import Util.position;

public class WhileStNode extends StmtNode{
    public ExprNode cond;
    public StmtNode stmt;

    public WhileStNode(position _pos, ExprNode _cond, StmtNode _stmt) {
        super(_pos);
        cond = _cond; stmt = _stmt;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
