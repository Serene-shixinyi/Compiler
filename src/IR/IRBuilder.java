package IR;

import AST.*;
import IR.IRtype.*;
import IR.inst.*;
import IR.operand.*;
import Util.error.unexpectedError;
import Util.symbol.Type;
import org.antlr.v4.runtime.misc.Pair;

import java.util.*;


//How do IR scope like? Maybe it's different from AST scope. So let's create a new one.
//Maybe it's awful.
//varibal: scope's operand is lvalue(address)....

public class IRBuilder implements ASTVisitor {
    public Function curfunc;
    public BasicBlock curblock;
    public IRScope curscope;
    public ClassType curclass;

    public Stack<BasicBlock> BreakBlock;
    public Stack<BasicBlock> ContinueBlock;
    public Function init;
    public Root root;

    public IRBuilder() {
        root = new Root();
        curfunc = null;
        curblock = null;
        curclass = null;
        BreakBlock = new Stack<>();
        ContinueBlock = new Stack<>();
        curscope = root.globalScope;
        init = new Function("__init__", new VoidType(), false);
        init.initialize(null, root.globalScope);
        curscope.add_func("__init", init);
    }

    public Operand arrayMalloc(int dim, ArrayList<Operand> sizeList, BaseType type) {
        Function func = root.globalScope.get_func("_malloc");
        ArrayList<Operand> parameters = new ArrayList<>();
        Register mallocrs = new Register(new PointerType(new IntType(8)), "malloc");

        int baseSize = type.size() / 8; //bytes
        Register bytesMul = new Register(new IntType(32), "bytesMul");
        Register bytes = new Register(new IntType(32), "bytes");
        curblock.add_inst(new Binary(bytesMul, curblock, sizeList.get(dim), new ConstInt(baseSize, 32), Binary.IRBinaryOp.mul));
        curblock.add_inst(new Binary(bytes, curblock, bytesMul, new ConstInt(4, 32), Binary.IRBinaryOp.add));
        parameters.add(bytes);
        curblock.add_inst(new Call(mallocrs, curblock, func, parameters));
        //cast into int32
        Register mallocInt32 = new Register(new PointerType(new IntType(32)), "mallocInt32");
        curblock.add_inst(new BitCastTo(mallocInt32, curblock, new PointerType(new IntType(32)), mallocrs));

        curblock.add_inst(new Store(curblock, mallocInt32, sizeList.get(dim))); // store the size of array
        Register arrayHeadInt32 = new Register(new PointerType(new IntType(32)), "arrayHeadInt32");
        ArrayList<Operand> index = new ArrayList<>();
        index.add(new ConstInt(1, 32));
        curblock.add_inst(new GetElementPtr(arrayHeadInt32, curblock, index, mallocInt32));
        //cast into object type
        Register arrayHead = new Register(type, "arrayHead");
        curblock.add_inst(new BitCastTo(arrayHead, curblock, type, arrayHeadInt32));

        if(dim == sizeList.size() - 1) return arrayHead;

        //next dim
        Register arrayTail = new Register(type, "arrayTail");
        index = new ArrayList<>();
        index.add(sizeList.get(dim));
        curblock.add_inst(new GetElementPtr(arrayTail, curblock, index, arrayHead));
        Register arrayPtrAddr = new Register(new PointerType(type), "arrayPtr$addr");
        curfunc.entryBlock.add_inst_front(new Allo(arrayPtrAddr, curfunc.entryBlock, type));
        curblock.add_inst(new Store(curblock, arrayPtrAddr, arrayHead));

        //loop
        BasicBlock condBlock = new BasicBlock(curfunc, "newLoopCond");
        BasicBlock bodyBlock = new BasicBlock(curfunc, "newLoopBody");
        BasicBlock mergeBlock = new BasicBlock(curfunc, "newLoopMerge");
        curblock.add_inst(new Jump(curblock, condBlock));
        curblock = condBlock; //condition
        Register arrayPointer = new Register(type, "arrayPointer");
        Register cmpResult = new Register(new IntType(8), "cmpResult");
        //arrayPointer = *arrayPtrAddr
        curblock.add_inst(new Load(arrayPointer, curblock, arrayPtrAddr));
        //cmpResult = arrayPointer < arrayTail
        curblock.add_inst(new Icmp(cmpResult, curblock, arrayPointer, arrayTail ,Icmp.IRIcmpOp.slt, type));
        curblock.add_inst(new Branch(curblock, cmpResult, bodyBlock, mergeBlock));
        curfunc.add_block(condBlock);
        curblock = bodyBlock; //body
        Operand arrayHeadNextDim = arrayMalloc(dim + 1, sizeList, ((PointerType) type).pointTo);
        //*arrayPointer = arrayHeadNextDim
        curblock.add_inst(new Store(curblock, arrayPointer, arrayHeadNextDim));
        Register nextArrayPtr = new Register(type, "nextArrayPtr");
        //nextArrayPtr = arrayPointer + "1"
        index = new ArrayList<>();
        index.add(new ConstInt(1, 32));
        curblock.add_inst(new GetElementPtr(nextArrayPtr, curblock, index, arrayPointer));
        //*arrayPtrAddr = nextArrayPtr
        curblock.add_inst(new Store(curblock, arrayPtrAddr, nextArrayPtr));
        curblock.add_inst(new Jump(curblock, condBlock));
        curfunc.add_block(bodyBlock);
        curblock = mergeBlock;
        curfunc.add_block(mergeBlock);
        return arrayHead;
    }

    public int get_member_pos(ClassType cur, String idtf) {
        int pos = 0;
        boolean fl = false;
        for(VarDclNode vardcl: cur.dclnode.varDcList) {
            if(vardcl instanceof SimpleDclNode) {
                for(OneDclNode var: ((SimpleDclNode) vardcl).dcls) {
                    if(var.identifier.equals(idtf)) { fl = true; break; }
                    ++pos;
                }
                if(fl) break;
            }
            else {
                if(!(vardcl instanceof OneDclNode)) throw new unexpectedError("vardcl in class error in IR");
                if(((OneDclNode) vardcl).identifier.equals(idtf)) { fl = true; break; }
                ++pos;
            }
        }
        if(!fl) throw new unexpectedError("can't find member of class in IR");
        return pos;
    }

    //Type Node
    @Override
    public void visit(IntTypeNode node){throw new unexpectedError("visit type node in IR");}
    @Override
    public void visit(BoolTypeNode node){throw new unexpectedError("visit type node in IR");}
    @Override
    public void visit(StringTypeNode node){throw new unexpectedError("visit type node in IR");}
    @Override
    public void visit(ClassTypeNode node) {throw new unexpectedError("visit type node in IR");}
    @Override
    public void visit(ArrayTypeNode node){throw new unexpectedError("visit type node in IR");}
    @Override
    public void visit(VoidTypeNode node){throw new unexpectedError("visit type node in IR");}

    //Expression Node
    @Override
    public void visit(NewExprNode node){
        if(node.dimnum == 0) {
            //we don't have "new int" or "new bool"....
            //so this is only for class
            ClassType type = (ClassType)(root.get_type(node.type.typename));
            Function func = root.globalScope.get_func("_malloc");
            int size = type.size();
            ArrayList<Operand> parameters = new ArrayList<>();
            parameters.add(new ConstInt(size, 32));
            Register rs = new Register(new PointerType(new IntType(8)), "malloc");
            Register cast = new Register(type, "classPtr");
            curblock.add_inst(new Call(rs, curblock, func, parameters));
            curblock.add_inst(new BitCastTo(cast, curblock, type, rs));

            if(type.constructor != null) {
                parameters = new ArrayList<>();
                parameters.add(cast);
                curblock.add_inst(new Call(null, curblock, type.constructor, parameters));
            }
            node.operand = cast;
            node.loperand = null;
        }
        else {
            //array
            List<ExprNode> dimExpr = node.exprlist;
            BaseType type = root.get_type(node.type.typename);
            for (int i = 0; i < node.dimnum; i++)
                type = new PointerType(type);
            ArrayList<Operand> sizeList = new ArrayList<>();
            for (ExprNode expr : dimExpr) {
                expr.accept(this); // visit ExprNode
                sizeList.add(expr.operand);
            }
            //new an array is so complicated. we need another 80LOCs to do this
            //writing codegen is like eating shit!
            node.operand = arrayMalloc(0, sizeList, type);
            node.loperand = null;
        }
    }
    @Override
    public void visit(FuncCallExprNode node){
        //so long, so tired, so many bugs
        ExprNode funcexpr = node.func;
        Function func = null;
        if(funcexpr instanceof ClassMemberNode) { //funcexpr: expr.idtf
            ExprNode expr = ((ClassMemberNode) funcexpr).expr;
            String idtf = ((ClassMemberNode) funcexpr).identifier;
            expr.accept(this); //don't visit funcexpr~~~
            Type type = expr.type;
            Operand exprrs = expr.operand;
            if(type.is_array()) {
                //array function: size()
                if(!idtf.equals("size")) throw new unexpectedError("array function not size() in IR");
                Register pointer;
                if (!exprrs.type.equals(new PointerType(new IntType(32)))) {
                    pointer = new Register(new PointerType(new IntType(32)), "cast");
                    curblock.add_inst(new BitCastTo(pointer, curblock, new PointerType(new IntType(32)), pointer));
                }
                else pointer = (Register)exprrs;
                ArrayList<Operand> index = new ArrayList<>();
                index.add(new ConstInt(-1, 32));
                Register addr = new Register(pointer.type, "elementPtr");
                Register size = new Register(new IntType(32), "arraySize");
                curblock.add_inst(new GetElementPtr(addr, curblock, index, pointer));
                curblock.add_inst(new Load(size, curblock, addr));
                node.operand = size;
                node.loperand = null;
            }
            else {
                if (type.typename.equals("string")) func = root.globalScope.get_func("__string_" + idtf);
                else func = root.globalScope.get_func(type.typename + "." + idtf);
                if(func == null) throw new unexpectedError("can't find func in funcCall in IR");

                ArrayList<Operand> parameters = new ArrayList<>();
                BaseType returnType = func.type;
                Register rs = returnType instanceof VoidType ? null : new Register(returnType, "call");
                parameters.add(exprrs); //this
                for (ExprNode prmt : node.prmtlist) {
                    prmt.accept(this);
                    parameters.add(prmt.operand);
                }
                curblock.add_inst(new Call(rs, curblock, func, parameters));
                node.operand = rs;
                node.loperand = null;
            }
        }
        else {
            if(!(funcexpr instanceof IdentifierExprNode))
                throw new unexpectedError("func call not a class or identifier in IR");
            String idtf = ((IdentifierExprNode) funcexpr).identifier;
            if(curclass != null)
                func = root.globalScope.get_func(curclass.name + "." + idtf);
            if(func == null) {// function out of class
                func = root.globalScope.get_func(idtf);
                if (func == null) throw new unexpectedError("can't find func in funcCall in IR");

                ArrayList<Operand> parameters = new ArrayList<>();
                BaseType returnType = func.type;
                Register rs = returnType instanceof VoidType ? null : new Register(returnType, "call");
                for (ExprNode prmt : node.prmtlist) {
                    prmt.accept(this);
                    parameters.add(prmt.operand);
                }
                curblock.add_inst(new Call(rs, curblock, func, parameters));
                node.operand = rs;
                node.loperand = null;
            }
            else {// function in class
                Register thisAddr = (Register) (curscope.get_var("this"));
                if(thisAddr == null) throw new unexpectedError("can't find thisAddr in func call in IR");
                Register thisrs = new Register(curclass, "this");
                curblock.add_inst(new Load(thisrs, curblock, thisAddr));
                BaseType returnType = func.type;
                Register rs = returnType instanceof VoidType? null : new Register(returnType, "call");
                ArrayList<Operand> parameters = new ArrayList<>();
                parameters.add(thisrs);
                for (ExprNode prmt : node.prmtlist) {
                    prmt.accept(this); // visit ExprNode
                    parameters.add(prmt.operand);
                }
                curblock.add_inst(new Call(rs, curblock, func, parameters));
                node.operand = rs;
                node.loperand = null;
            }
        }
    }
    @Override
    public void visit(ClassMemberNode node){ //not a function, because we do all things in FuncCallExprNode
        node.expr.accept(this);
        ClassType classtype = (ClassType) root.get_type(node.expr.type.typename);
        if(classtype == null || (!(classtype instanceof ClassType)))
            throw new unexpectedError("can't find class of member node in IR");
        String idtf = node.identifier;

        int pos = get_member_pos(classtype, idtf);
        BaseType type = classtype.members.get(pos);
        Operand ptr = node.expr.operand;
        ArrayList<Operand> index = new ArrayList<>();
        index.add(new ConstInt(0, 32));
        index.add(new ConstInt(pos, 32));
        Register rsaddr = new Register(new PointerType(type), classtype.name + "." + idtf + "$addr");
        Register rs = new Register(type, classtype.name + "." + idtf);

        curblock.add_inst(new GetElementPtr(rsaddr, curblock, index, ptr));
        curblock.add_inst(new Load(rs, curblock, rsaddr));
        node.operand = rs;
        node.loperand = rsaddr;
    }
    @Override
    public void visit(ArrayIndexNode node){
        node.array.accept(this);
        node.index.accept(this);
        Operand arrayPtr = node.array.operand;
        ArrayList<Operand> index = new ArrayList<>();
        index.add(node.index.operand);
        Register rsaddr = new Register(arrayPtr.type, "elementPtr");
        curblock.add_inst(new GetElementPtr(rsaddr, curblock, index, arrayPtr));
        Register rs = new Register(((PointerType) arrayPtr.type).pointTo, "arrayLoad");
        curblock.add_inst(new Load(rs, curblock, rsaddr));
        node.operand = rs;
        node.loperand = rsaddr;
    }
    @Override
    public void visit(SuffixOpNode node){
        node.expr.accept(this);
        Register rs;
        Operand exprvalue = node.expr.operand;
        Operand expraddr = node.expr.loperand;
        if(expraddr == null) throw new unexpectedError("suffix expr can't be lvalue in IR");
        if(node.op == SuffixOpNode.Op.DEC) {
            rs = new Register(new IntType(32), "suffixDec");
            curblock.add_inst(new Binary(rs, curblock, exprvalue, new ConstInt(1, 32), Binary.IRBinaryOp.sub));
        }
        else {
            rs = new Register(new IntType(32), "suffixInc");
            curblock.add_inst(new Binary(rs, curblock, exprvalue, new ConstInt(1, 32), Binary.IRBinaryOp.add));
        }
        curblock.add_inst(new Store(curblock, expraddr, rs));
        node.operand = exprvalue;
        node.loperand = null;
    }
    @Override
    public void visit(PrefixOpNode node){
        node.expr.accept(this);
        Register rs;
        Operand exprvalue = node.expr.operand;
        Operand expraddr = node.expr.loperand;
        if(expraddr == null) throw new unexpectedError("prefix expr can't be lvalue in IR");
        if(node.op == PrefixOpNode.Op.DEC) {
            rs = new Register(new IntType(32), "prefixDec");
            curblock.add_inst(new Binary(rs, curblock, exprvalue, new ConstInt(1, 32), Binary.IRBinaryOp.sub));
        }
        else {
            rs = new Register(new IntType(32), "prefixInc");
            curblock.add_inst(new Binary(rs, curblock, exprvalue, new ConstInt(1, 32), Binary.IRBinaryOp.add));
        }
        curblock.add_inst(new Store(curblock, expraddr, rs));
        node.operand = rs;
        node.loperand = expraddr;
    }
    @Override
    public void visit(UnaryOpNode node){
        node.expr.accept(this);
        Operand exprvalue = node.expr.operand;
        Operand expraddr = node.expr.loperand;
        if(expraddr == null) throw new unexpectedError("prefix expr can't be lvalue in IR");
        if(node.op == UnaryOpNode.Op.NEG) {
            if(exprvalue instanceof ConstInt) {
                node.operand = new ConstInt(-((ConstInt) exprvalue).value, 32);
            }
            else {
                Register rs = new Register(new IntType(32), "signNeg");
                curblock.add_inst(new Binary(rs, curblock, new ConstInt(0, 32), exprvalue, Binary.IRBinaryOp.sub));
                node.operand = rs;
            }
        }
        else if(node.op == UnaryOpNode.Op.POS){
            node.operand = exprvalue;
        }
        else if(node.op == UnaryOpNode.Op.NOT) { // ~
            Register rs = new Register(new IntType(32), "bitwiseComplement");
            curblock.add_inst(new Binary(rs, curblock, new ConstInt(-1,  32), exprvalue, Binary.IRBinaryOp.xor));
            node.operand = rs;
        }
        else if(node.op == UnaryOpNode.Op.NOT2) { // !
            Register rs = new Register(new IntType(8), "bitwiseComplement"); //bool
            curblock.add_inst(new Binary(rs, curblock, new ConstInt(1,  32), exprvalue, Binary.IRBinaryOp.xor));
            node.operand = rs;
        }
        node.loperand = null;
    }
    @Override
    public void visit(BinaryOpNode node){
        //long and boring
        BinaryOpNode.Op op = node.op;
        if(op != BinaryOpNode.Op.AND2 && op != BinaryOpNode.Op.OR2) {
            node.l.accept(this);
            node.r.accept(this);
            Operand lrs = node.l.operand, rrs = node.r.operand;
            if (op == BinaryOpNode.Op.ADD) {
                if(node.l.type.is_int() && node.r.type.is_int()) {
                    Register rs = new Register(new IntType(32), "add");
                    curblock.add_inst(new Binary(rs, curblock, lrs, rrs, Binary.IRBinaryOp.add));
                    node.operand = rs;
                    node.loperand = null;
                }
                else {
                    Function func = root.globalScope.get_func("__string_concatenate");
                    ArrayList<Operand> parameters = new ArrayList<>();
                    parameters.add(lrs);
                    parameters.add(rrs);
                    Register rs = new Register(new PointerType(new IntType(8)), "stringConcatenate");
                    curblock.add_inst(new Call(rs, curblock, func, parameters));
                    node.operand = rs;
                    node.loperand = null;
                }
            }
            else if (op == BinaryOpNode.Op.SUB) {
                Register rs = new Register(new IntType(32), "sub");
                curblock.add_inst(new Binary(rs, curblock, lrs, rrs, Binary.IRBinaryOp.sub));
                node.operand = rs;
                node.loperand = null;
            }
            else if (op == BinaryOpNode.Op.MUL) {
                Register rs = new Register(new IntType(32), "mul");
                curblock.add_inst(new Binary(rs, curblock, lrs, rrs, Binary.IRBinaryOp.mul));
                node.operand = rs;
                node.loperand = null;
            }
            else if(op == BinaryOpNode.Op.DIV) {
                Register rs = new Register(new IntType(32), "div");
                curblock.add_inst(new Binary(rs, curblock, lrs, rrs, Binary.IRBinaryOp.sdiv));
                node.operand = rs;
                node.loperand = null;
            }
            else if(op == BinaryOpNode.Op.MOD) {
                Register rs = new Register(new IntType(32), "mod");
                curblock.add_inst(new Binary(rs, curblock, lrs, rrs, Binary.IRBinaryOp.srem));
                node.operand = rs;
                node.loperand = null;
            }
            else if (op == BinaryOpNode.Op.SHL) {
                Register rs = new Register(new IntType(32), "shl");
                curblock.add_inst(new Binary(rs, curblock, lrs, rrs, Binary.IRBinaryOp.shl));
                node.operand = rs;
                node.loperand = null;
            }
            else if (op == BinaryOpNode.Op.SHR) {
                Register rs = new Register(new IntType(32), "shr");
                curblock.add_inst(new Binary(rs, curblock, lrs, rrs, Binary.IRBinaryOp.ashr));
                node.operand = rs;
                node.loperand = null;
            }
            else if (op == BinaryOpNode.Op.AND) {
                Register rs = new Register(new IntType(32), "and");
                curblock.add_inst(new Binary(rs, curblock, lrs, rrs, Binary.IRBinaryOp.and));
                node.operand = rs;
                node.loperand = null;
            }
            else if (op == BinaryOpNode.Op.OR) {
                Register rs = new Register(new IntType(32), "or");
                curblock.add_inst(new Binary(rs, curblock, lrs, rrs, Binary.IRBinaryOp.or));
                node.operand = rs;
                node.loperand = null;
            }
            else if (op == BinaryOpNode.Op.XOR) {
                Register rs = new Register(new IntType(32), "xor");
                curblock.add_inst(new Binary(rs, curblock, lrs, rrs, Binary.IRBinaryOp.xor));
                node.operand = rs;
                node.loperand = null;
            }
            else if (op == BinaryOpNode.Op.LT) { // <
                if(node.l.type.is_int() && node.r.type.is_int()) {
                    Register rs = new Register(new IntType(8), "lessThan");
                    curblock.add_inst(new Icmp(rs, curblock, lrs, rrs, Icmp.IRIcmpOp.slt, new IntType(32)));
                    node.operand = rs;
                    node.loperand = null;
                }
                else {
                    Function func = root.globalScope.get_func("__string_lessThan");
                    ArrayList<Operand> parameters = new ArrayList<>();
                    parameters.add(lrs);
                    parameters.add(rrs);
                    Register rs = new Register(new IntType(8), "stringLessThan"); //bool
                    curblock.add_inst(new Call(rs, curblock, func, parameters));
                    node.operand = rs;
                    node.loperand = null;
                }
            }
            else if (op == BinaryOpNode.Op.GT) { // >
                if(node.l.type.is_int() && node.r.type.is_int()) {
                    Register rs = new Register(new IntType(8), "greaterThan");
                    curblock.add_inst(new Icmp(rs, curblock, lrs, rrs, Icmp.IRIcmpOp.sgt, new IntType(32)));
                    node.operand = rs;
                    node.loperand = null;
                }
                else {
                    Function func = root.globalScope.get_func("__string_greaterThan");
                    ArrayList<Operand> parameters = new ArrayList<>();
                    parameters.add(lrs);
                    parameters.add(rrs);
                    Register rs = new Register(new IntType(8), "stringGreaterThan"); //bool
                    curblock.add_inst(new Call(rs, curblock, func, parameters));
                    node.operand = rs;
                    node.loperand = null;
                }
            }
            else if (op == BinaryOpNode.Op.LEQ) { // <=
                if(node.l.type.is_int() && node.r.type.is_int()) {
                    Register rs = new Register(new IntType(8), "lessEqual");
                    curblock.add_inst(new Icmp(rs, curblock, lrs, rrs, Icmp.IRIcmpOp.sle, new IntType(32)));
                    node.operand = rs;
                    node.loperand = null;
                }
                else {
                    Function func = root.globalScope.get_func("__string_lessEqual");
                    ArrayList<Operand> parameters = new ArrayList<>();
                    parameters.add(lrs);
                    parameters.add(rrs);
                    Register rs = new Register(new IntType(8), "stringLessEqual"); //bool
                    curblock.add_inst(new Call(rs, curblock, func, parameters));
                    node.operand = rs;
                    node.loperand = null;
                }
            }
            else if (op == BinaryOpNode.Op.GEQ) { // >=
                if(node.l.type.is_int() && node.r.type.is_int()) {
                    Register rs = new Register(new IntType(8), "greaterEqual");
                    curblock.add_inst(new Icmp(rs, curblock, lrs, rrs, Icmp.IRIcmpOp.sge, new IntType(32)));
                    node.operand = rs;
                    node.loperand = null;
                }
                else {
                    Function func = root.globalScope.get_func("__string_greaterEqual");
                    ArrayList<Operand> parameters = new ArrayList<>();
                    parameters.add(lrs);
                    parameters.add(rrs);
                    Register rs = new Register(new IntType(8), "stringGreaterEqual"); //bool
                    curblock.add_inst(new Call(rs, curblock, func, parameters));
                    node.operand = rs;
                    node.loperand = null;
                }
            }
            else if (op == BinaryOpNode.Op.EQ) { // ==
                if(node.l.type.is_int() && node.r.type.is_int()) {
                    Register rs = new Register(new IntType(8), "intEqual");
                    curblock.add_inst(new Icmp(rs, curblock, lrs, rrs, Icmp.IRIcmpOp.eq, new IntType(32)));
                    node.operand = rs;
                    node.loperand = null;
                }
                else if(node.l.type.is_bool() && node.r.type.is_bool()) {
                    Register rs = new Register(new IntType(8), "boolEqual");
                    curblock.add_inst(new Icmp(rs, curblock, lrs, rrs, Icmp.IRIcmpOp.eq, new IntType(8)));
                    node.operand = rs;
                    node.loperand = null;
                }
                else if(node.l.type.is_array() && node.r.type.is_null()) { //arr == null
                    Register rs = new Register(new IntType(8), "arrayEqual");
                    curblock.add_inst(new Icmp(rs, curblock, lrs, rrs, Icmp.IRIcmpOp.eq, lrs.type));
                    node.operand = rs;
                    node.loperand = null;
                }
                else if(node.l.type.is_null() && node.r.type.is_array()) { //null == array
                    Register rs = new Register(new IntType(8), "arrayEqual");
                    curblock.add_inst(new Icmp(rs, curblock, lrs, rrs, Icmp.IRIcmpOp.eq, rrs.type));
                    node.operand = rs;
                    node.loperand = null;
                }
                else if(node.l.type.is_class() && node.r.type.is_null()) { //class == null
                    Register rs = new Register(new IntType(8), "classEqual");
                    curblock.add_inst(new Icmp(rs, curblock, lrs, rrs, Icmp.IRIcmpOp.eq, lrs.type));
                    node.operand = rs;
                    node.loperand = null;
                }
                else if(node.l.type.is_null() && node.r.type.is_class()) { //null == class
                    Register rs = new Register(new IntType(8), "classEqual");
                    curblock.add_inst(new Icmp(rs, curblock, lrs, rrs, Icmp.IRIcmpOp.eq, rrs.type));
                    node.operand = rs;
                    node.loperand = null;
                }
                else if(node.l.type.is_null() && node.r.type.is_null()) { //null == null
                    node.operand = new ConstBool(true);
                    node.loperand = null;
                }
                else {
                    Function func = root.globalScope.get_func("__string_equal");
                    ArrayList<Operand> parameters = new ArrayList<>();
                    parameters.add(lrs);
                    parameters.add(rrs);
                    Register rs = new Register(new IntType(8), "stringEqual"); //bool
                    curblock.add_inst(new Call(rs, curblock, func, parameters));
                    node.operand = rs;
                    node.loperand = null;
                }
            }
            else if (op == BinaryOpNode.Op.ASSIGN) {
                curblock.add_inst(new Store(curblock, node.l.loperand, rrs));
                node.operand = rrs;
                node.loperand = null;
            }
        }
        else if(op == BinaryOpNode.Op.AND2) {
            BasicBlock branchBlock = new BasicBlock(curfunc, "logicalAndBranch");
            BasicBlock mergeBlock = new BasicBlock(curfunc, "logicalAndMerge");
            BasicBlock phi1, phi2;

            node.l.accept(this);
            Operand lrs = node.l.operand;
            curblock.add_inst(new Branch(curblock, lrs, branchBlock, mergeBlock));
            phi1 = curblock;

            curblock = branchBlock;
            node.r.accept(this);
            Operand rrs = node.l.operand;
            curblock.add_inst(new Jump(curblock, mergeBlock));
            curfunc.add_block(branchBlock);
            phi2 = curblock;

            curblock = mergeBlock;
            Register rs = new Register(new IntType(8), "logicalAnd");
            Set<Pair<Operand, BasicBlock>> branch = new LinkedHashSet<>();
            branch.add(new Pair<>(new ConstBool(false), phi1));
            branch.add(new Pair<>(rrs, phi2));
            curblock.add_inst(new Phi(rs, curblock, branch));
            curfunc.add_block(mergeBlock);

            node.operand = rs;
            node.loperand = null;
        }
        else {//OR2
            BasicBlock branchBlock = new BasicBlock(curfunc, "logicalOrBranch");
            BasicBlock mergeBlock = new BasicBlock(curfunc, "logicalOrMerge");
            BasicBlock phi1, phi2;

            node.l.accept(this);
            Operand lrs = node.l.operand;
            curblock.add_inst(new Branch(curblock, lrs, mergeBlock, branchBlock));
            phi1 = curblock;

            curblock = branchBlock;
            node.r.accept(this);
            Operand rrs = node.l.operand;
            curblock.add_inst(new Jump(curblock, mergeBlock));
            curfunc.add_block(branchBlock);
            phi2 = curblock;

            curblock = mergeBlock;
            Register rs = new Register(new IntType(8), "logicalOr");
            Set<Pair<Operand, BasicBlock>> branch = new LinkedHashSet<>();
            branch.add(new Pair<>(new ConstBool(true), phi1));
            branch.add(new Pair<>(rrs, phi2));
            curblock.add_inst(new Phi(rs, curblock, branch));
            curfunc.add_block(mergeBlock);

            node.operand = rs;
            node.loperand = null;
        }
    }
    @Override
    public void visit(IdentifierExprNode node){ //can't be in A.idtf
        String idtf = node.identifier;
        Operand var = curscope.get_var(idtf);
        if(var == null) {
            if(curclass == null) throw new unexpectedError("can't find identifier in IR");
            Register thisaddr = (Register) curscope.get_var("this");
            Register thisvalue = new Register(curclass, "this");
            curblock.add_inst(new Load(thisvalue, curblock, thisaddr));
            int pos = get_member_pos(curclass, idtf);
            BaseType type = curclass.members.get(pos);
            ArrayList<Operand> index = new ArrayList<>();
            index.add(new ConstInt(0, 32));
            index.add(new ConstInt(pos, 32));
            Register rsaddr = new Register(new PointerType(type), curclass.name + "." + idtf + "$addr");
            Register rs = new Register(type, curclass.name + "." + idtf);
            curblock.add_inst(new GetElementPtr(rsaddr, curblock, index, thisvalue));
            curblock.add_inst(new Load(rs, curblock, rsaddr));
            node.operand = rs;
            node.loperand = rsaddr;
        }
        else {
            BaseType type = null;
            if(var instanceof GlobalVar) type = var.type;
            else type = ((PointerType)var.type).pointTo;
            Register rs = new Register(type, node.identifier);
            curblock.add_inst(new Load(rs, curblock, var));
            node.operand = rs;
            node.loperand = var;
        }
    }
    @Override
    public void visit(ThisExprNode node){
        Register rsaddr = (Register) curscope.get_var("this");
        BaseType type = ((PointerType) rsaddr.type).pointTo;
        Register rs = new Register(type, "this");
        curblock.add_inst(new Load(rs, curblock, rsaddr));
        node.operand = rs;
        node.loperand = null; //we don't use this
    }

    @Override
    public void visit(IntConstNode node){
        node.operand = new ConstInt(node.val, 32);
    }
    @Override
    public void visit(StringConstNode node){
        String str = root.change_string(node.val);
        GlobalVar var = root.globalScope.get_str(node.val);
        if(var == null) {
            String name = ".str." + root.get_lable();
            var = new GlobalVar(new ArrayType(str.length(),new IntType(8)), name);
            var.operand = new ConstStr(new ArrayType(str.length(), new IntType(8)), str);
            root.globalScope.add_var(name, var);

            ArrayList<Operand> index = new ArrayList<>();
            index.add(new ConstInt( 0, 32));
            index.add(new ConstInt( 0, 32));
            Register rs = new Register(new PointerType(new IntType(8)),"ConstString");
            curblock.add_inst(new GetElementPtr(rs, curblock, index, var));
        }
    }
    @Override
    public void visit(BoolConstNode node){
        node.operand = new ConstBool(node.val);
        node.loperand = null;
    }
    @Override
    public void visit(NullConstNode node){
        node.operand = new Null();
        node.loperand = null;
    }

    //Statement Node
    @Override
    public void visit(BlockStNode node){
        IRScope tmp = curscope; //add a new scope
        curscope = new IRScope(tmp);
        node.stmtlist.forEach(x -> x.accept(this));
        curscope = tmp;
    }
    @Override
    public void visit(ExprStNode node){
        node.expr.accept(this);
    }

    @Override
    public void visit(AssignDclNode node) {
        BaseType irType = root.get_type(node.dcl.type.typeIdentifier);
        node.dcl.accept(this);
        node.expr.accept(this);
        if(curclass != null) throw new unexpectedError("assign dcl in class dcl during IR");
        if(curscope == root.globalScope) {
            GlobalVar now = (GlobalVar) curscope.get_var(node.dcl.identifier);
            now.operand = node.expr.operand;
            curblock.add_inst(new Store(curblock, now, now.operand));
        }
        else {
            Register allocAddr = (Register)curscope.get_var(node.dcl.identifier);
            curblock.add_inst(new Store(curblock, allocAddr, node.expr.operand));
        }
    }
    @Override
    public void visit(OneDclNode node){
        BaseType irType = root.get_type(node.type.typeIdentifier);
        if(curclass != null) {
            curclass.addMember(irType);
            return;
        }
        if(curscope != root.globalScope) {
            Register allocAddr = new Register(new PointerType(irType), node.identifier + "$addr");
            BasicBlock entryBlock = curfunc.entryBlock;
            curscope.add_var(node.identifier, allocAddr);
            entryBlock.add_inst_front(new Allo(allocAddr, entryBlock, irType));
        }
        else curscope.add_var(node.identifier, new GlobalVar(irType, node.identifier));
    }
    @Override
    public void visit(SimpleDclNode node){
        node.dcls.forEach(x -> x.accept(this));
    }

    @Override
    public void visit(ReturnNode node){
        if(node.expr != null) {
            node.expr.accept(this);
            Operand rs = node.expr.operand;
            curblock.add_inst(new Store(curblock, curfunc.returnAddr, rs));
        }
        curblock.add_inst(new Jump(curblock, curfunc.returnBlock));
    }
    @Override
    public void visit(BreakNode node){
        curblock.add_inst(new Jump(curblock, BreakBlock.peek()));
    }
    @Override
    public void visit(ContinueNode node){
        curblock.add_inst(new Jump(curblock, ContinueBlock.peek()));
    }
    @Override
    public void visit(IfelseStNode node){
        BasicBlock thenBlock = new BasicBlock(curfunc, "ifThenBlock");
        BasicBlock elseBlock = new BasicBlock(curfunc, "ifElseBlock");
        BasicBlock mergeBlock = new BasicBlock(curfunc, "ifMergeBlock");

        node.cond.accept(this); // we don't need a cond block
        Operand condrs = node.cond.operand;

        curblock.add_inst(new Branch(curblock, condrs, thenBlock, elseBlock));

        IRScope tmp = curscope;
        curscope = new IRScope(tmp);//add a scope
        curblock = thenBlock;
        node.stmt1.accept(this);
        curblock.add_inst(new Jump(curblock, mergeBlock));
        curfunc.add_block(thenBlock);

        curscope = new IRScope(tmp);//add a scope
        curblock = elseBlock;
        if(node.stmt2 != null) node.stmt2.accept(this);
        curblock.add_inst(new Jump(curblock, mergeBlock));
        curfunc.add_block(elseBlock);
        curscope = tmp;

        curblock = mergeBlock;
        curfunc.add_block(mergeBlock);
    }

    @Override
    public void visit(ForStNode node){
        IRScope tmp = curscope; //add a scope
        curscope = new IRScope(tmp);

        BasicBlock condBlock = new BasicBlock(curfunc, "forCondBlock");
        BasicBlock stepBlock = new BasicBlock(curfunc, "forStepBlock");
        BasicBlock bodyBlock = new BasicBlock(curfunc, "forBodyBlock");
        BasicBlock mergeBlock = new BasicBlock(curfunc, "forMergeBlock");

        if(node.init != null)  {
            node.init.accept(this);
        }

        curblock.add_inst(new Jump(curblock, condBlock));
        curblock = condBlock;
        Operand condrs;
        if(node.cond != null) {
            node.cond.accept(this);
            condrs = node.cond.operand;
        }
        else condrs = new ConstBool(true);
        curblock.add_inst(new Branch(curblock, condrs, bodyBlock, mergeBlock));
        curfunc.add_block(condBlock); //add condblock to curfunc

        BreakBlock.push(mergeBlock);
        ContinueBlock.push(stepBlock);
        curblock = bodyBlock;
        node.stmt.accept(this);
        curblock.add_inst(new Jump(curblock, stepBlock));
        curfunc.add_block(bodyBlock); //add bodyblock to curfunc

        BreakBlock.pop();
        ContinueBlock.pop();

        curblock = stepBlock;
        if(node.incr != null) node.incr.accept(this);
        curblock.add_inst(new Jump(curblock, condBlock));
        curfunc.add_block(stepBlock); // add stepblock to curfunc

        curblock = mergeBlock;
        curfunc.add_block(mergeBlock); //add mergeblock to curfunc

        curscope = tmp;
    }
    @Override
    public void visit(WhileStNode node){
        IRScope tmp = curscope; //add a scope
        curscope = new IRScope(tmp);

        BasicBlock condBlock = new BasicBlock(curfunc, "whileCondBlock");
        BasicBlock bodyBlock = new BasicBlock(curfunc, "whileBodyBlock");
        BasicBlock mergeBlock = new BasicBlock(curfunc, "whileMergeBlock");

        curblock.add_inst(new Jump(curblock, condBlock));

        curblock = condBlock;
        node.cond.accept(this);
        Operand condrs = node.cond.operand;
        curblock.add_inst(new Branch(curblock, condrs, bodyBlock, mergeBlock));
        curfunc.add_block(condBlock); //add condblock to curfunc

        BreakBlock.push(mergeBlock);
        ContinueBlock.push(condBlock);
        curblock = bodyBlock;
        node.stmt.accept(this);
        curblock.add_inst(new Jump(curblock, condBlock));
        curfunc.add_block(bodyBlock); //add bodyblock to curfunc

        BreakBlock.pop();
        ContinueBlock.pop();

        curblock = mergeBlock;
        curfunc.add_block(mergeBlock); //add mergeblock to curfunc

        curscope = tmp;
    }

    @Override
    public void visit(MainFuncDclNode node){
        OtherFuncDclNode _node = new OtherFuncDclNode(node.pos, node.block,
                new IntTypeNode(node.pos), "main", null);
        _node.accept(this);
        // we will do "init" first in main
        Call tmp = new Call(null, curfunc.entryBlock, init, new ArrayList<>());
        curfunc.entryBlock.add_inst_front(tmp);
        curfunc = null;
        curblock = null;
    }
    @Override
    public void visit(OtherFuncDclNode node){
        String funcname = node.identifier;
        if(curclass != null)
            funcname = curclass.name + "." + node.identifier;
        Function func = root.globalScope.get_func(funcname);
        if(func == null) throw new unexpectedError("can't find function in cur scope: " + funcname);

        IRScope tmp = curscope; //add a scope
        curscope = new IRScope(tmp);
        curfunc = func;
        curfunc.initialize(curclass, curscope); // initialize
        curblock = func.entryBlock;
        node.block.accept(this);
        curscope = tmp;

        curblock.add_inst(new Jump(curblock, curfunc.returnBlock));//default return
        curfunc.add_block(curfunc.returnBlock); //add return block to curfunc
        if(!(curfunc.name.equals("main"))) curfunc = null;
        curblock = null;
    }
    @Override
    public void visit(ClassDclNode node){
        throw new unexpectedError("visit ClassDclNode during IR");
    }

    @Override
    public void visit(ProgramNode node){
        curscope = root.globalScope;
        for(DclNode dcl: node.dcls) { // add class (type)
            if(dcl instanceof ClassDclNode) {
                curclass = new ClassType(((ClassDclNode) dcl).identifier);
                curclass.dclnode = (ClassDclNode) dcl;
                root.add_class(curclass);
            }
        }
        for(DclNode dcl: node.dcls) {
            if(dcl instanceof ClassDclNode) {
                curclass = (ClassType) (root.get_type(((ClassDclNode) dcl).identifier));
                // visit vardcl in class(no assignment): Just add them to classtype
                for (VarDclNode vardcl : ((ClassDclNode) dcl).varDcList) {
                    vardcl.accept(this);
                }
                // add funcdcl in class
                for (FuncDclNode funcdcl: ((ClassDclNode) dcl).funcDclList) {
                    String func_name = ((ClassDclNode) dcl).identifier + "." + ((OtherFuncDclNode)funcdcl).identifier;
                    curfunc = new Function(func_name, root.get_type(((OtherFuncDclNode) funcdcl).type.typeIdentifier), false);
                    //add a parameter "this" to our functions in class
                    curfunc.add_parameter(new Register(new PointerType(curclass), "this$addr"));
                    curfunc.add_all_parameters(funcdcl, root);
                    if(((OtherFuncDclNode) funcdcl).is_constructor) {
                        if(curclass.constructor != null)
                            throw new unexpectedError("class has many constructors in IR");
                        curclass.constructor = curfunc;
                    }
                    root.globalScope.add_func(func_name, curfunc);
                }
            }
        }
        curclass = null;
        for(DclNode dcl: node.dcls) { // add function out of class
            if(dcl instanceof FuncDclNode) {
                if(dcl instanceof MainFuncDclNode) {
                    curfunc = new Function("main", root.get_type("int"), false);
                    curfunc.add_all_parameters((MainFuncDclNode)dcl, root); // do not have parameters
                    root.globalScope.add_func("main", curfunc);
                }
                if(dcl instanceof OtherFuncDclNode) {
                    BaseType irType = root.get_type(((OtherFuncDclNode) dcl).type.typeIdentifier);
                    curfunc = new Function(((OtherFuncDclNode) dcl).identifier, irType, false);
                    curfunc.add_all_parameters((OtherFuncDclNode)dcl, root);
                    root.globalScope.add_func(((OtherFuncDclNode) dcl).identifier, curfunc);
                }
            }
        }
        curclass = null;
        curfunc = init; //"init" has initialized, it's used to declare global varibles
        curblock = init.entryBlock;
        for(DclNode dcl: node.dcls) { // visit global vardcl out of class
            if(dcl instanceof VarDclNode) {
                dcl.accept(this);
            }
        }
        curblock.add_inst(new Jump(curblock, curfunc.returnBlock));
        curfunc.add_block(init.returnBlock); //add return block to curfunc
        curfunc = null;
        curblock = null;

        for(DclNode dcl: node.dcls) { // visit funcdcl in class
            if(dcl instanceof ClassDclNode) {
                curclass = (ClassType)(root.get_type(((ClassDclNode) dcl).identifier));
                for(FuncDclNode funcdcl: ((ClassDclNode)dcl).funcDclList) {
                    funcdcl.accept(this);
                }
            }
        }
        curclass = null;
        for(DclNode dcl: node.dcls) { // visit funcdcl out of class
            if(dcl instanceof FuncDclNode) {
                dcl.accept(this);
            }
        }
    }
}
