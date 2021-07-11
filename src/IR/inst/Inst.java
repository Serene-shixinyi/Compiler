package IR.inst;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.operand.Register;

public abstract class Inst {
    public Register dest;
    public Inst pre, nxt;
    public BasicBlock bb;
    public Inst(Register _dest, BasicBlock _bb) {
        dest = _dest;
        bb = _bb;
        pre = nxt = null;
    }

    public abstract String toString();
    public abstract void accept(IRVisitor visitor);
}
