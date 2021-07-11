package IR.inst;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.operand.Operand;

public class Branch extends Inst{
    public Operand cond;
    public BasicBlock dest1, dest2;

    public Branch(BasicBlock _bb, Operand _cond, BasicBlock _dest1, BasicBlock _dest2) {
        super(null, _bb);
        cond = _cond;
        dest1 = _dest1;
        dest2 = _dest2;
//        cond.addUse(this);
    }

    @Override
    public String toString() {
        return "br " + cond.type.toString() + " " + cond.toString() + ", label %" +
                dest1.name + ", label %" + dest2.name;
    }
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
