package IR.inst;

import IR.BasicBlock;
import IR.Function;
import IR.IRVisitor;
import IR.operand.Operand;
import IR.operand.Register;

import java.util.ArrayList;

public class Call extends Inst{ // call func, store result in dest
    public Function func;
    public ArrayList<Operand> params;
    public Call(Register _dest, BasicBlock _bb, Function _func, ArrayList<Operand> _params) {
        super(_dest, _bb);
        func = _func;
        params = _params;
//        params.forEach(param -> param.addUse(this));
//        if (dest != null) dest.setDef(this);
    }
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        if (dest != null) {
            ret.append(dest.toString());
            ret.append(" = ");
            ret.append("call ");
            ret.append(dest.type.toString());
            ret.append(" ");
        }
        else ret.append("call void ");
        ret.append("@");
        ret.append(func.name);
        if (params.size() == 0) ret.append("(");
        for (int i = 0;i < params.size();++i){
            ret.append((i == 0 ? "(" : ", "));
            ret.append(params.get(i).type.toString());
            ret.append(" ");
            ret.append(params.get(i).toString());
        }
        ret.append(")");
        return ret.toString();
    }
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
