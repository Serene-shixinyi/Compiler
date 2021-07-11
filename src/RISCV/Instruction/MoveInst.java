package RISCV.Instruction;

import RISCV.ASMBasicBlock;
import RISCV.Operand.Register.VirtualReg;

public class MoveInst extends ASMInst{
    public VirtualReg rd;
    public VirtualReg rs;

    public MoveInst(ASMBasicBlock _bb, VirtualReg _rd, VirtualReg _rs) {
        super(_bb);
        rd = _rd;
        rs = _rs;
    }
}
