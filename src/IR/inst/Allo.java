package IR.inst;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.IRtype.BaseType;
import IR.operand.Register;

public class Allo extends Inst{
    BaseType type;
    public Allo(Register _dest, BasicBlock _bb, BaseType _type) {
        super(_dest, _bb);
        type = _type;
    }

    public String toString() {
        return dest.toString() + " = alloca " + type.toString();
    }
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
