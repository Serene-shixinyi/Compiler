package RISCV.Instruction;

import RISCV.ASMBasicBlock;

public class JumpInst extends ASMInst{
    public ASMBasicBlock jumpDest;
    public JumpInst(ASMBasicBlock _bb, ASMBasicBlock _jumpDest) {
        super(_bb);
        jumpDest = _jumpDest;
    }
}
