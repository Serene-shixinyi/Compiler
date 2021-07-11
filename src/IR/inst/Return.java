package IR.inst;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.operand.Operand;

public class Return extends Inst{
    public Operand value;
    public Return(BasicBlock _bb, Operand _value) {
        super(null, _bb);
        value = _value;
//        if (_value != null) _value.addUse(this);
    }

    @Override
    public String toString() {
        return "ret " + (value == null ? "void" :
                (value.type.toString() + " " + value.toString()));
    }
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
