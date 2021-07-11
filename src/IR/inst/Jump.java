package IR.inst;

import IR.BasicBlock;
import IR.IRVisitor;

public class Jump extends Inst{
    public BasicBlock jumpDest;
    public Jump(BasicBlock _bb, BasicBlock _jumpDest) {
        super(null, _bb);
        jumpDest = _jumpDest;
    }
    @Override
    public String toString() {
        return "br label %" + jumpDest.name;
    }
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
