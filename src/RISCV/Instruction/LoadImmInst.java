package RISCV.Instruction;

import RISCV.ASMBasicBlock;
import RISCV.Operand.Immediate.ASMImmediate;
import RISCV.Operand.Register.VirtualReg;

public class LoadImmInst extends ASMInst{
    public VirtualReg rd;
    public ASMImmediate imm;
    public LoadImmInst(ASMBasicBlock _bb, VirtualReg _rd, ASMImmediate _imm) {
        super(_bb);
        rd = _rd;
        imm = _imm;
    }
}
