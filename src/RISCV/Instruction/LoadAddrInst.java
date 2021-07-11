package RISCV.Instruction;

import RISCV.ASMBasicBlock;
import RISCV.Operand.ASMGlobalVar;
import RISCV.Operand.Register.VirtualReg;

public class LoadAddrInst extends ASMInst{
    public VirtualReg rd;
    public ASMGlobalVar var;

    public LoadAddrInst(ASMBasicBlock _bb, VirtualReg _rd, ASMGlobalVar _var) {
        super(_bb);
        rd = _rd;
        var = _var;
    }
}
