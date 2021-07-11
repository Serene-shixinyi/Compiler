package RISCV.Instruction.Binary;

import RISCV.ASMBasicBlock;
import RISCV.Operand.Register.VirtualReg;

public class RBinaryInst extends BinaryInst{
    public enum RBinaryOp {
        add, sub, mul, div, rem, sll, sra, and, or, xor, slt
    }

    public IBinaryInst.IBinaryOp op;
    public VirtualReg rs2;

    public RBinaryInst(ASMBasicBlock _bb, VirtualReg _rd, VirtualReg _rs1, VirtualReg _rs2, IBinaryInst.IBinaryOp _op) {
        super(_bb, _rd, _rs1);
        rs2 = _rs2;
        op = _op;
    }
}
