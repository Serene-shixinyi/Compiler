package RISCV.Instruction;

import RISCV.ASMBasicBlock;
import RISCV.Operand.Address.ASMAddress;
import RISCV.Operand.Register.VirtualReg;

public class StoreInst extends ASMInst{
    public enum ByteSize {
        sb, sw
    }
    public VirtualReg rs;
    public LoadInst.ByteSize byteSize;
    public ASMAddress addr;
    public StoreInst(ASMBasicBlock _bb, VirtualReg _rs, LoadInst.ByteSize _byteSize, ASMAddress _addr) {
        super(_bb);
        rs = _rs;
        byteSize = _byteSize;
        addr = _addr;
    }
}
