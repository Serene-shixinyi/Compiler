package IR.inst;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.operand.Operand;
import IR.operand.Register;

public class Binary extends Inst{
    public enum IRBinaryOp {
        mul, sdiv, srem, shl, ashr, and, or, xor, sub, add
        //*   /     %    <<    >>    &    |   ^    -    +
    }
    public IRBinaryOp op;
    public Operand l, r;

    public Binary(Register _dest, BasicBlock _bb, Operand _l, Operand _r, IRBinaryOp _op) {
        super(_dest, _bb);
        l = _l; r = _r; op = _op;
    }

    @Override
    public String toString(){
        return dest.toString() + " = " + op.toString() + " " +
                l.type.toString() + " " + l.toString() + ", " + r.toString();
    }
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
