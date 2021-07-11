package RISCV.Instruction;

import RISCV.ASMBasicBlock;

public abstract class ASMInst {
    public ASMInst pre, nxt;
    public ASMBasicBlock bb;
    public ASMInst(ASMBasicBlock _bb) {
        bb = _bb;
        pre = nxt = null;
    }
}
