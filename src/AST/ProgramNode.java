package AST;

import Util.position;
import java.util.List;

public class ProgramNode extends ASTNode{
    public List<DclNode> dcls;
    public ProgramNode(position _pos, List<DclNode> _dcls) {
        super(_pos); dcls = _dcls;
    }
    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
