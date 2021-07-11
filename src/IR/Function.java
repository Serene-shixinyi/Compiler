package IR;

import AST.FuncDclNode;
import AST.MainFuncDclNode;
import AST.OneDclNode;
import AST.OtherFuncDclNode;
import IR.IRtype.*;
import IR.inst.*;
import IR.operand.*;

import java.util.ArrayList;

public class Function {
    public BasicBlock entryBlock, returnBlock, exitBlock;
    public String name;
    public BaseType type;
    public Register returnAddr;
    public boolean is_external;

    public ArrayList<Register> parameters;

    public Function(String _name, BaseType _type, boolean _is_external) {
        name = _name;
        type = _type;
        parameters = new ArrayList<>();
        is_external = _is_external;
    }
    public void add_parameter(Register _prmt) {
        parameters.add(_prmt);
    }
    public void add_all_parameters(FuncDclNode dcl, Root root) {
        // do not add "this" in add_all_parameters(...)
        if(dcl instanceof MainFuncDclNode) return;
        for(OneDclNode onedcl: ((OtherFuncDclNode) dcl).dcls.dcls) {
            Register tmp = new Register(root.get_type(onedcl.type.typeIdentifier), onedcl.identifier);
            add_parameter(tmp);
        }
    }
    public void add_block(BasicBlock _bb) { // not sure yet
        if(entryBlock == null) {
            entryBlock = exitBlock = _bb;
            return;
        }
        exitBlock.nxt = _bb;
        _bb.pre = exitBlock;
        exitBlock = _bb;
    }
    public void initialize(ClassType curclass, IRScope curscope) {
        entryBlock = new BasicBlock(this, "entryBlock");
        exitBlock = entryBlock;
        returnBlock = new BasicBlock(this, "returnBlock");
        if(type instanceof VoidType) {
            returnBlock.add_inst(new Return(returnBlock, null));
        }
        else {
            returnAddr = new Register(new PointerType(type), "returnAddr");
            entryBlock.add_inst(new Allo(returnAddr, entryBlock, type));

            Register returnValue = new Register(type, "returnValue");
            returnBlock.add_inst(new Load(returnValue, returnBlock, returnAddr));
            returnBlock.add_inst(new Return(returnBlock, returnValue));
        }
        //add instructions of parameters
        //please add all parameters before this
        BasicBlock curblock = entryBlock;
        int i = 0;
        if(curclass != null) {
            Register prmt = parameters.get(0); //this
            Register allocaAddr = new Register(new PointerType(prmt.type), "this$addr");
            curblock.add_inst(new Allo(allocaAddr, curblock, prmt.type));
            curblock.add_inst(new Store(curblock, allocaAddr, prmt));
            curscope.add_var("this", allocaAddr);
            i = 1;
        }
        for (;i < parameters.size(); ++i) {
            Register prmt = parameters.get(i);
            Register allocaAddr = new Register(new PointerType(prmt.type), prmt.name + "$addr");
            curblock.add_inst(new Allo(allocaAddr, curblock, prmt.type));
            curblock.add_inst(new Store(curblock, allocaAddr, prmt));
            curscope.add_var(prmt.name, allocaAddr);
        }
    }
    public String declareToString() {
        StringBuilder tmp = new StringBuilder("declare ");
        tmp.append(type.toString());
        tmp.append(" @").append(name);

        tmp.append("(");
        for (int i = 0; i < parameters.size(); i++) {
            Register prmt = parameters.get(i);
            tmp.append(prmt.type.toString()).append(" ");
            tmp.append(prmt.toString());
            if (i != parameters.size() - 1)
                tmp.append(", ");
        }
        tmp.append(")");

        return tmp.toString();
    }
    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
