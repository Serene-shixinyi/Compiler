package IR.inst;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.operand.Operand;
import IR.operand.Register;
import org.antlr.v4.runtime.misc.Pair;

import java.util.Set;

public class Phi extends Inst{
    Set<Pair<Operand, BasicBlock>> branch;
    public Phi(Register _dest, BasicBlock _bb, Set<Pair<Operand, BasicBlock>> _branch) {
        super(_dest, _bb);
        branch = _branch;
    }

    public String toString(){
        StringBuilder ret = new StringBuilder(dest.toString() + " = phi " + dest.type.toString() + " ");
        int size = branch.size();
        int cnt = 0;
        for (Pair<Operand, BasicBlock> pair : branch) {
            ret.append("[ ").append(pair.a.toString()).append(", ").append(pair.b.toString()).append(" ]");
            if (++cnt != size)
                ret.append(", ");
        }
        return ret.toString();
    }
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
