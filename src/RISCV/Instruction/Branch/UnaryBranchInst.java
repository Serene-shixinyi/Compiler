package RISCV.Instruction.Branch;

import RISCV.ASMBasicBlock;
import RISCV.Operand.Register.VirtualReg;

public class UnaryBranchInst extends BranchInst{
    public enum UnaryBranchOp {
        beqz, bnez, bltz, bgez, blez, bgtz
    }
    public UnaryBranchOp op;
    public UnaryBranchInst(ASMBasicBlock _bb, ASMBasicBlock _thenBlock, VirtualReg _rs1, UnaryBranchOp _op) {
        super(_bb, _thenBlock, _rs1);
        op = _op;
    }
}
