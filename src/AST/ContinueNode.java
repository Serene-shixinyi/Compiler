package AST;

import Util.position;

public class ContinueNode extends StmtNode{
//    public Loop loop;

    public ContinueNode(position _pos) {
        super(_pos);
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
