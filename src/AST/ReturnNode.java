package AST;

import Util.position;

public class ReturnNode extends StmtNode{
    public ExprNode expr;

    public ReturnNode(position _pos, ExprNode _expr) {
        super(_pos);
        expr = _expr;
    }
    
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
