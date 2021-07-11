package RISCV.Instruction.Binary;

import RISCV.ASMBasicBlock;
import RISCV.Operand.Immediate.ASMImmediate;
import RISCV.Operand.Register.VirtualReg;

public class IBinaryInst extends BinaryInst{
    public enum IBinaryOp {
        addi, slli, srai, andi, ori, xori, slti
    }

    public IBinaryOp op;
    public ASMImmediate imm;

    public IBinaryInst(ASMBasicBlock _bb, VirtualReg _rd, VirtualReg _rs1, ASMImmediate _imm, IBinaryOp _op) {
        super(_bb, _rd, _rs1);
        imm = _imm;
        op = _op;
    }
}
