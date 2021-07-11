package RISCV.Instruction;

import RISCV.ASMBasicBlock;
import RISCV.ASMFunction;

public class CallInst extends ASMInst{
    ASMFunction func;
    public CallInst(ASMBasicBlock _bb, ASMFunction _func) {
        super(_bb);
        func = _func;
    }
}
