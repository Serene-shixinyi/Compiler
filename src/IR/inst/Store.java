package IR.inst;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.IRtype.PointerType;
import IR.operand.Null;
import IR.operand.Operand;

public class Store extends Inst{
    public Operand value;
    public Operand address;

    public Store(BasicBlock _bb, Operand _address, Operand _value) {
        super(null, _bb);
        value = _value;
        address = _address;
//        value.addUse(this);
//        address.addUse(this);
    }

    @Override
    public String toString() {
        String vs = value.type.toString();
        if (value instanceof Null) vs = ((PointerType)address.type).pointTo.toString();
        return "store " + vs + " " + value.toString() + ", " +
                address.type.toString() + " " + address.toString() +
                ", align " + value.type.size() / 8;
    }
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
