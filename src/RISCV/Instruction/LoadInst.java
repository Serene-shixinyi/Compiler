package RISCV.Instruction;

import RISCV.ASMBasicBlock;
import RISCV.Operand.Address.ASMAddress;
import RISCV.Operand.Register.VirtualReg;

public class LoadInst extends ASMInst{
    public enum ByteSize {
        lb, lw
    }
    public VirtualReg rd;
    public ByteSize byteSize;
    public ASMAddress addr;
    public LoadInst(ASMBasicBlock _bb, VirtualReg _rd, ByteSize _byteSize, ASMAddress _addr) {
        super(_bb);
        rd = _rd;
        byteSize = _byteSize;
        addr = _addr;
    }
}
