package RISCV.Instruction.Branch;

import RISCV.ASMBasicBlock;
import RISCV.Instruction.ASMInst;
import RISCV.Operand.Register.VirtualReg;

public abstract class BranchInst extends ASMInst {
    public VirtualReg rs1;
    public ASMBasicBlock thenBlock;

    public BranchInst(ASMBasicBlock _bb, ASMBasicBlock _thenBlock, VirtualReg _rs1) {
        super(_bb);
        thenBlock = _thenBlock;
        rs1 = _rs1;
    }
}
