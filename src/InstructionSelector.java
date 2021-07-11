import IR.BasicBlock;
import IR.Function;
import IR.IRVisitor;
import IR.Root;
import IR.inst.*;
import RISCV.ASMBasicBlock;
import RISCV.ASMFunction;
import RISCV.ASMRoot;

public class InstructionSelector implements IRVisitor {
    public ASMRoot asmroot;
    public ASMFunction curfunc;
    public ASMBasicBlock curblock;
    public InstructionSelector() {
        asmroot = new ASMRoot();
        curfunc = null;
        curblock = null;
    }
    //Root
    @Override
    public void visit(Root root){}
    //Function
    @Override
    public void visit(Function function){}
    //BasicBlock
    @Override
    public void visit(BasicBlock block){}
    //Instuction
    @Override
    public void visit(Allo inst){}
    @Override
    public void visit(Binary inst){}
    @Override
    public void visit(BitCastTo inst){}
    @Override
    public void visit(Branch inst){}
    @Override
    public void visit(Call inst){}
    @Override
    public void visit(GetElementPtr inst){}
    @Override
    public void visit(Icmp inst){}
    @Override
    public void visit(Jump inst){}
    @Override
    public void visit(Load inst){}
    @Override
    public void visit(Phi inst){}
    @Override
    public void visit(Return inst){}
    @Override
    public void visit(Store inst){}
}
