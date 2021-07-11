package IR;

import IR.inst.Inst;

public class BasicBlock {
    public BasicBlock pre, nxt;
    public Inst fir, lst;
    public String name;
    public Function func;
    public BasicBlock(Function _func, String _name) {
        func = _func;
        name = _name;
        fir = lst = null;
        pre = nxt = null;
    }
    public boolean is_empty() {
        return fir == lst && fir == null;
    }
    public void add_inst(Inst now) {
        if(is_empty()) {
            fir = now;
            lst = now;
        }
        else {
            lst.nxt = now;
            now.pre = lst;
            lst = now;
        }
    }
    public void add_inst_front(Inst now) {
        if(is_empty()) {
            fir = now;
            lst = now;
        }
        else {
            fir.pre = now;
            now.nxt = fir;
            fir = now;
        }
    }
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
