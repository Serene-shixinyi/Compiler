package IR;

import IR.inst.*;

public interface IRVisitor {
    //Root
    public void visit(Root root);
    //Function
    public void visit(Function function);
    //BasicBlock
    public void visit(BasicBlock block);
    //Instuction
    public void visit(Allo inst);
    public void visit(Binary inst);
    public void visit(BitCastTo inst);
    public void visit(Branch inst);
    public void visit(Call inst);
    public void visit(GetElementPtr inst);
    public void visit(Icmp inst);
    public void visit(Jump inst);
    public void visit(Load inst);
    public void visit(Phi inst);
    public void visit(Return inst);
    public void visit(Store inst);
}
