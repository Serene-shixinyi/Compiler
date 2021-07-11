package RISCV.Instruction.Branch;

import RISCV.ASMBasicBlock;
import RISCV.Operand.Register.VirtualReg;

public class BinaryBranchInst extends BranchInst{
    public enum BinaryBranchOp {
        beq, bne, blt, bge, ble, bgt
    }
    public BinaryBranchOp op;
    public VirtualReg rs2;

    public BinaryBranchInst(ASMBasicBlock _bb, ASMBasicBlock _thenBlock, VirtualReg _rs1, VirtualReg _rs2, BinaryBranchOp _op) {
        super(_bb, _thenBlock, _rs1);
        rs2 = _rs2;
        op = _op;
    }
}
