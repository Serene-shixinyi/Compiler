package RISCV;

import IR.BasicBlock;
import RISCV.Instruction.ASMInst;

public class ASMBasicBlock {
    public ASMInst fir, lst;
    public ASMBasicBlock pre, nxt;
    public String name;
    public String asmname;
    public ASMFunction func;
    public BasicBlock irblock;

    public ASMBasicBlock(ASMFunction _func, BasicBlock _irblock, String _name, String _asmname) {
        func = _func;
        irblock = _irblock;
        name = _name;
        asmname = _asmname;
        fir = lst = null;
        pre = nxt = null;
    }
    public boolean is_empty() {
        return fir == lst && fir == null;
    }
}
