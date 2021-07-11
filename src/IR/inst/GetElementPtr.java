package IR.inst;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.IRtype.BaseType;
import IR.IRtype.PointerType;
import IR.operand.Operand;
import IR.operand.Register;

import java.util.ArrayList;

public class GetElementPtr extends Inst{
    public Operand pointer;
    public ArrayList<Operand> index;
    public GetElementPtr(Register _dest, BasicBlock _bb, ArrayList<Operand> _index, Operand _pointer) {
        super(_dest, _bb);
        index = _index;
        pointer = _pointer;
    }
    @Override
    public String toString() {
        BaseType baseType;
        BaseType pointerType;
        if (pointer.type instanceof PointerType) {
            baseType = ((PointerType) pointer.type).pointTo;
            pointerType = pointer.type;
        } else {
            baseType = pointer.type;
            pointerType = new PointerType(baseType);
        }
        StringBuilder string = new StringBuilder();
        string.append(dest.toString()).append(" = ");
        string.append("getelementptr ").append(baseType.toString()).append(", ");
        string.append(pointerType).append(" ").append(pointer.toString());
        for (Operand ind : index)
            string.append(", ").append(ind.type.toString()).append(" ").append(ind.toString());
        return string.toString();
    }
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
