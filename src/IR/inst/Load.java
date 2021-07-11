package IR.inst;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.operand.Operand;
import IR.operand.Register;

public class Load extends Inst{
    public Operand address;
    public Load(Register _dest, BasicBlock _bb, Operand _address) {
        super(_dest, _bb);
        address = _address;
//        address.addUse(this);
//        dest.setDef(this);
    }

    @Override
    public String toString() {
        return "%" + dest.name + " = load " + dest.type.toString() + ", "
                + address.type.toString() + " " + address.toString() + ", align " +
                dest.type.size() / 8;
    }
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
