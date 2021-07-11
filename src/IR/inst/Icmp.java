package IR.inst;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.IRtype.BaseType;
import IR.IRtype.IntType;
import IR.IRtype.PointerType;
import IR.operand.Null;
import IR.operand.Operand;
import IR.operand.Register;
import Util.error.unexpectedError;

public class Icmp extends Inst{
    public enum IRIcmpOp{
        eq, ne, sgt, sge, slt, sle
    }// ==  !=  >    >=   <    <=
    public IRIcmpOp op;
    public Operand l, r;
    public BaseType type;

    public Icmp(Register _dest, BasicBlock _bb, Operand _l, Operand _r, IRIcmpOp _op, BaseType _type) {
        super(_dest, _bb);
        l = _l; r = _r; op = _op;
        type = _type;

        int o = 0, o1 = 0, o2 = 0, o3 = 0;
        if(type.equals(l.type) || (l instanceof Null && type instanceof PointerType)) {++o; ++o1;}
        if(type.equals(r.type) || (r instanceof Null && type instanceof PointerType)) {++o; ++o2;}
        if(dest.type.equals(new IntType(8))) {++o; ++o3;} // bool:8
        if(o < 3) throw new unexpectedError("Icmp used wrong: " + dest.type.toString() + ", "
                + type.toString() + " " + l.type.toString() + " " + r.type.toString());
    }
    @Override
    public String toString() {
        return dest.toString() + " = icmp " + op.name() + " "
                + type.toString() + " " + l.toString() + ", " + r.toString();
    }
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
