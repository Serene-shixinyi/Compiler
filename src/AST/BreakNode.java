package AST;

import Util.position;

public class BreakNode extends StmtNode{
//    public Loop loop;

    public BreakNode(position _pos) {
        super(_pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
