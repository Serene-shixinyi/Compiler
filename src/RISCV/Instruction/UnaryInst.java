package RISCV.Instruction;

import RISCV.ASMBasicBlock;
import RISCV.Operand.Register.VirtualReg;

public class UnaryInst extends ASMInst{
    public enum UnaryOp {
        seqz, snez, sltz, sgtz
    }
    public UnaryOp op;
    public VirtualReg rd;
    public VirtualReg rs;
    public UnaryInst(ASMBasicBlock _bb, UnaryOp _op, VirtualReg _rs, VirtualReg _rd) {
        super(_bb);
        op = _op;
        rs = _rs;
        rd = _rd;
    }
}
