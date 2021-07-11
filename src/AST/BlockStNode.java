package AST;

import Util.position;

import java.util.List;

public class BlockStNode extends StmtNode{
    public List<StmtNode> stmtlist;
    public position pos;

    public BlockStNode(position _pos, List<StmtNode> _stmtlist) {
        super(_pos);
        stmtlist = _stmtlist;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
