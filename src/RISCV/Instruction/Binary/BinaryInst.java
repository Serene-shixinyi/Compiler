package RISCV.Instruction.Binary;

import RISCV.ASMBasicBlock;
import RISCV.Instruction.ASMInst;
import RISCV.Operand.Register.VirtualReg;

public abstract class BinaryInst extends ASMInst {
    public VirtualReg rd;
    public VirtualReg rs1;

    public BinaryInst(ASMBasicBlock _bb, VirtualReg _rd, VirtualReg _rs1) {
        super(_bb);
        rd = _rd;
        rs1 = _rs1;
    }
}
