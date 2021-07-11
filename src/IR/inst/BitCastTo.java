package IR.inst;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.IRtype.BaseType;
import IR.operand.Operand;
import IR.operand.Register;

public class BitCastTo extends Inst{
    public Operand src;
    public BaseType type;

    public BitCastTo(Register _dest, BasicBlock _bb, BaseType _type, Operand _src) {
        super(_dest, _bb);
        type = _type;
        src = _src;
    }

    @Override
    public String toString() {
        return dest.toString() + " = bitcast "
                + src.type.toString() + " " + src.toString() + " to " + type.toString();
    }
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
