package Frontend;

import AST.*;
import Util.error.semanticError;
import Util.error.unexpectedError;
import Util.symbol.*;

public class SemanticChecker implements ASTVisitor {
    GlobalScope globalscope;
    LocalScope curscope;
    int loopcnt;
    Type funcType;
    ClassSymbol nowclass;
    public SemanticChecker() {
        globalscope = new GlobalScope();
        globalscope.classdefine(new ClassSymbol("int", null, null));
        globalscope.classdefine(new ClassSymbol("bool", null, null));
        globalscope.classdefine(new ClassSymbol("void", null, null));
        //please check void

        FuncSymbol now = null;

        GlobalScope stringscope = new GlobalScope();
        stringscope.funcdefine(new FuncSymbol("length", new Type("int", 0), null));
        now = new FuncSymbol("substring", new Type("string", 0), null);
        now.prmts.add(new Type("int", 0));
        now.prmts.add(new Type("int", 0));
        stringscope.funcdefine(now);
        stringscope.funcdefine(new FuncSymbol("parseInt", new Type("int", 0), null));
        now = new FuncSymbol("ord", new Type("int", 0), null);
        now.prmts.add(new Type("int", 0));
        stringscope.funcdefine(now);
        globalscope.classdefine(new ClassSymbol("string", stringscope, null));

        now = new FuncSymbol("print", new Type("void", 0), null);
        now.prmts.add(new Type("string", 0));
        globalscope.funcdefine(now);
        now = new FuncSymbol("println", new Type("void", 0), null);
        now.prmts.add(new Type("string", 0));
        globalscope.funcdefine(now);
        now = new FuncSymbol("printInt", new Type("void", 0), null);
        now.prmts.add(new Type("int", 0));
        globalscope.funcdefine(now);
        now = new FuncSymbol("printlnInt", new Type("void", 0), null);
        now.prmts.add(new Type("int", 0));
        globalscope.funcdefine(now);
        globalscope.funcdefine(new FuncSymbol("getString", new Type("string", 0), null));
        globalscope.funcdefine(new FuncSymbol("getInt", new Type("int", 0), null));
        now = new FuncSymbol("toString", new Type("string", 0), null);
        now.prmts.add(new Type("int", 0));
        globalscope.funcdefine(now);
    }

    @Override public void visit(IntTypeNode node){}
    @Override public void visit(BoolTypeNode node){}
    @Override public void visit(StringTypeNode node){}
    @Override public void visit(ClassTypeNode node){} //Identifier
    @Override public void visit(ArrayTypeNode node){}
    @Override public void visit(VoidTypeNode node){}

    @Override
    public void visit(NewExprNode node) {
        node.type = new Type(node.identifier, node.dimnum);
        int num = node.exprlist.size();
        node.exprlist.forEach(x->x.accept(this));
        for (int i = 0; i < num; ++i) {
            if(!node.exprlist.get(i).type.is_int()) {
                throw new semanticError("New array index must be int type",
                        node.exprlist.get(i).pos);
            }
        }
        node.category = ExprNode.Category.RVALUE;
    }

    @Override
    public void visit(FuncCallExprNode node) {
        if(node.func instanceof ClassMemberNode) {
            ((ClassMemberNode) node.func).expr.accept(this);
            if(((ClassMemberNode) node.func).expr.type.dim > 0) {
                if(!((ClassMemberNode) node.func).identifier.equals("size")) {
                    throw new semanticError("Array doesn't has this function", node.func.pos);
                }
                node.type = new Type("int", 0);
                node.category = ExprNode.Category.RVALUE;
            }
            else {
                if (!((ClassMemberNode) node.func).expr.type.is_class()) {
                    throw new semanticError("This is not a class",
                                    ((ClassMemberNode) node.func).expr.pos);
                }
                ClassSymbol now = (ClassSymbol) curscope.find_classsymbol(
                            ((ClassMemberNode) node.func).expr.type.typename,
                            ((ClassMemberNode) node.func).expr.pos
                );
                FuncSymbol func = (FuncSymbol) now.scope.find_funcsymbol(
                            ((ClassMemberNode) node.func).identifier,
                            node.func.pos
                            );
                int num, i = 0;
                if((num = func.prmts.size()) != node.prmtlist.size())
                    throw new semanticError("Number of parameters doesn't match", node.pos);
                node.prmtlist.forEach(x -> x.accept(this));
                for(; i < num; ++i) {
                    if(!func.prmts.get(i).can_assign(node.prmtlist.get(i).type)) {
                        throw new semanticError("Type of parameters doesn't match", node.pos);
                    }
                }
                node.type = func.type;
                if(node.type.is_void()) node.category = ExprNode.Category.VOIDFUNCTION;
                else node.category = ExprNode.Category.RVALUE;
            }
        }
        else if(node.func instanceof IdentifierExprNode) {
            FuncSymbol func = (FuncSymbol) curscope.find_funcsymbol(
                    ((IdentifierExprNode) node.func).identifier,
                    node.pos
                    );
            int num, i = 0;
            if((num = func.prmts.size()) != node.prmtlist.size())
                throw new semanticError("Number of parameters doesn't match", node.pos);
            node.prmtlist.forEach(x -> x.accept(this));
            for(; i < num; ++i) {
                if(!func.prmts.get(i).can_assign(node.prmtlist.get(i).type)) {
                    throw new semanticError("Type of parameters doesn't match", node.pos);
                }
            }
            node.type = func.type;
            if(node.type.is_void()) node.category = ExprNode.Category.VOIDFUNCTION;
            else node.category = ExprNode.Category.RVALUE;
        }
        else throw new semanticError("This is not a function", node.func.pos);
    }

    @Override
    public void visit(ClassMemberNode node) {
        node.expr.accept(this);
        if(!node.expr.type.is_class()) {
            throw new semanticError("This is not a class", node.expr.pos);
        }
        ClassSymbol now = (ClassSymbol) curscope.find_classsymbol(node.expr.type.typename, node.expr.pos);
        VarSymbol var = (VarSymbol) now.scope.find_varsymbol(node.identifier, node.pos);
        node.type = var.type;
        node.category = ExprNode.Category.LVALUE;
    }//please don't use this for function

    public void visit(ArrayIndexNode node) {
        node.array.accept(this);
        node.index.accept(this);
        if(node.array.type.dim < 1)
            throw new semanticError("This is not an array", node.array.pos);
        if(!node.index.type.is_int())
            throw new semanticError("Array index must be type int", node.index.pos);
        node.type = new Type(node.array.type.typename, node.array.type.dim - 1);
        node.category = ExprNode.Category.LVALUE;
    }

    @Override
    public void visit(PrefixOpNode node) {
        node.expr.accept(this);
        if((!node.expr.type.is_int()) || (node.expr.category != ExprNode.Category.LVALUE))
            throw new semanticError("Operand error in unary operation", node.pos);
        node.type = new Type("int", 0);
        node.category = ExprNode.Category.LVALUE;
    }
    @Override
    public void visit(SuffixOpNode node) {
        node.expr.accept(this);
        if((!node.expr.type.is_int()) || (node.expr.category != ExprNode.Category.LVALUE))
            throw new semanticError("Operand error in unary operation", node.pos);
        node.type = new Type("int", 0);
        node.category = ExprNode.Category.RVALUE;
    }
    @Override
    public void visit(UnaryOpNode node) {
        node.expr.accept(this);
        if(node.op == UnaryOpNode.Op.NOT2) {
            if(!node.expr.type.is_bool())
                throw new semanticError("Operand error in unary operation", node.pos);
            node.type = new Type("bool", 0);
            node.category = ExprNode.Category.RVALUE;
        }
        else {
            if (!node.expr.type.is_int())
                throw new semanticError("Operand error in unary operation", node.pos);

            node.type = new Type("int", 0);
            node.category = ExprNode.Category.RVALUE;
        }
    }

    @Override
    public void visit(BinaryOpNode node) {
        node.r.accept(this);
        node.l.accept(this);
        if(node.op == BinaryOpNode.Op.ASSIGN || node.op == BinaryOpNode.Op.EQ ||
            node.op == BinaryOpNode.Op.NEQ) {
            if(!node.l.type.can_assign(node.r.type))
                throw new semanticError("Operands error in binary operation", node.pos);
        }
        else if(node.op == BinaryOpNode.Op.LT || node.op == BinaryOpNode.Op.GT ||
            node.op == BinaryOpNode.Op.LEQ || node.op == BinaryOpNode.Op.GEQ ||
            node.op == BinaryOpNode.Op.ADD) {
            if(!node.l.type.can_compare(node.r.type))
                throw new semanticError("Operands error in binary operation", node.pos);
        }
        else if(node.op == BinaryOpNode.Op.OR2 || node.op == BinaryOpNode.Op.AND2){
            if(!node.l.type.all_bool(node.r.type))
                throw new semanticError("Operands error in binary operation", node.pos);
        }
        else {
            if(!node.l.type.can_calculate(node.r.type))
                throw new semanticError("Operands error in binary operation", node.pos);
        }
        if(node.op == BinaryOpNode.Op.ASSIGN && node.l.category != ExprNode.Category.LVALUE)
            throw new semanticError("Operands error in binary operation", node.pos);

        if(node.op == BinaryOpNode.Op.LT || node.op == BinaryOpNode.Op.GT ||
                node.op == BinaryOpNode.Op.LEQ || node.op == BinaryOpNode.Op.GEQ ||
                node.op == BinaryOpNode.Op.EQ || node.op == BinaryOpNode.Op.NEQ ||
                node.op == BinaryOpNode.Op.OR2 || node.op == BinaryOpNode.Op.AND2) {
            node.type = new Type("bool", 0);
            node.category = ExprNode.Category.RVALUE;
        }
        else if(node.op == BinaryOpNode.Op.ASSIGN) {
            node.type = new Type(node.l.type.typename, node.l.type.dim);
            node.category = ExprNode.Category.LVALUE;
        }
        else {
            node.type = new Type(node.l.type.typename, 0);
            node.category = ExprNode.Category.RVALUE;
        }
    }

    @Override
    public void visit(IdentifierExprNode node) {
        VarSymbol now = (VarSymbol) curscope.find_varsymbol(node.identifier, node.pos);
        node.type = now.type;
        node.category = ExprNode.Category.LVALUE;
    } //please don't use this for function or class

    @Override
    public void visit(ThisExprNode node) {
        if(nowclass == null)
            throw new semanticError("Expression \"this\" must be in a class", node.pos);
        node.type = new Type(nowclass.identifier, 0);
        node.category = ExprNode.Category.THIS;
    }

    @Override
    public void visit(IntConstNode node) {
        node.type = new Type("int", 0);
        node.category = ExprNode.Category.RVALUE;
    }
    @Override
    public void visit(StringConstNode node) {
        node.type = new Type("string", 0);
        node.category = ExprNode.Category.RVALUE;
    }
    @Override
    public void visit(BoolConstNode node) {
        node.type = new Type("bool", 0);
        node.category = ExprNode.Category.RVALUE;
    }
    @Override
    public void visit(NullConstNode node) {
        node.type = new Type("null", 0);
        node.category = ExprNode.Category.RVALUE;
    }

    @Override
    public void visit(BlockStNode node) {
        LocalScope tmp = curscope;
        curscope = new LocalScope(tmp);
        int num = node.stmtlist.size();
        for(int i = 0; i < num; ++i) {
            if(node.stmtlist.get(i) != null)
                node.stmtlist.get(i).accept(this);
        }
        curscope = tmp;
    }

    @Override
    public void visit(ExprStNode node) {
        node.expr.accept(this);
    }

    @Override
    public void visit(OneDclNode node){
        if(node.type.typeIdentifier.equals("void"))
            throw new semanticError("Variables mustn't be type void", node.type.pos);
        curscope.find_classsymbol(node.type.typeIdentifier, node.type.pos);
        curscope.vardefine(new VarSymbol(node));
    }

    @Override
    public void visit(SimpleDclNode node) {
        node.dcls.forEach(x -> x.accept(this));
    }

    @Override
    public void visit(AssignDclNode node) {
        node.expr.accept(this);
        node.dcl.accept(this);
        Type nowtype = new Type(node.dcl.type);
        if(!nowtype.can_assign(node.expr.type))
            throw new semanticError("Assignment operation error", node.pos);
    }

    @Override
    public void visit(ReturnNode node) {
        if(node.expr!=null) node.expr.accept(this);

        if((node.expr != null) && node.expr.type.is_void())
            throw new semanticError("Return invalid void-type value", node.expr.pos);
        if(funcType == null) throw new unexpectedError("Return in no function");
        if(funcType.is_void() && (node.expr != null))
            throw new semanticError("Return expression in void function", node.pos);
        if((!funcType.is_void()) && node.expr == null)
            throw new semanticError("Return without expression in nonvoid function", node.pos);
        if((node.expr != null) && (!funcType.can_assign(node.expr.type)))
            throw new semanticError("Return expression type doesn't match function type", node.pos);
    }

    @Override
    public void visit(ContinueNode node) {
        if(loopcnt <= 0)
            throw new semanticError("Continue statement must be in a loop", node.pos);
    }

    @Override
    public void visit(BreakNode node) {
        if(loopcnt <= 0)
            throw new semanticError("Break statement must be in a loop", node.pos);
    }

    @Override
    public void visit(IfelseStNode node) {
        node.cond.accept(this);
        if(!node.cond.type.is_bool())
            throw new semanticError("Condition of \"if\" must be type bool", node.cond.pos);
        LocalScope tmp;
        if(node.stmt1!= null) {
            tmp = curscope;
            curscope = new LocalScope(tmp);
            node.stmt1.accept(this);
            curscope = tmp;
        }
        if(node.stmt2 != null) {
            tmp = curscope;
            curscope = new LocalScope(tmp);
            node.stmt2.accept(this);
            curscope = tmp;
        }
    }

    @Override
    public void visit(WhileStNode node) {
        LocalScope tmp = curscope;
        curscope = new LocalScope(tmp);
        ++loopcnt;
        node.cond.accept(this);
        if(!node.cond.type.is_bool())
            throw new semanticError("Condition of \"while\" must be type bool", node.cond.pos);
        if(node.stmt != null) node.stmt.accept(this);
        --loopcnt;
        curscope = tmp;
    }

    @Override
    public void visit(ForStNode node) {
        LocalScope tmp = curscope;
        curscope = new LocalScope(tmp);
        ++loopcnt;
        if(node.init!= null) node.init.accept(this);
        if(node.cond!= null) {
            node.cond.accept(this);
            if(!node.cond.type.is_bool())
                throw new semanticError("Condition of \"for\" must be type bool", node.cond.pos);
        }
        if(node.incr!= null) node.incr.accept(this);
        if(node.stmt != null) node.stmt.accept(this);
        --loopcnt;
        curscope = tmp;
    }

    @Override
    public void visit(OtherFuncDclNode node) {
        LocalScope tmp = curscope;
        curscope = new LocalScope(tmp);
        if(node.type == null)
            node.type = new VoidTypeNode(node.pos); //let constructor be void function
        else curscope.find_classsymbol(node.type.typeIdentifier, node.type.pos);

        for(OneDclNode dcl: node.dcls.dcls) {
            curscope.vardefine(new VarSymbol(dcl));
        }
        funcType = new Type(node.type);
        visit(node.block);
        funcType = null;
        curscope = tmp;
    }

    @Override
    public void visit(MainFuncDclNode node) {
        LocalScope tmp = curscope;
        curscope = new LocalScope(tmp);
        funcType = new Type("int", 0);
        visit(node.block);
        funcType = null;
        curscope = tmp;
    }

    @Override
    public void visit(ClassDclNode node) {
        LocalScope tmp = curscope;
        curscope = new LocalScope(tmp);
        get_classscope(node, curscope);
        nowclass = (ClassSymbol) globalscope.find_classsymbol(node.identifier, node.pos);
        for(VarDclNode vardcl: node.varDcList) {
            if(vardcl instanceof SimpleDclNode) {
                for(OneDclNode dcl : ((SimpleDclNode) vardcl).dcls) {
                    curscope.find_classsymbol(dcl.type.typeIdentifier, dcl.type.pos);
                }
            }
            if(vardcl instanceof OneDclNode)
                curscope.find_classsymbol(((OneDclNode) vardcl).type.typeIdentifier, vardcl.pos);
        }
        for(FuncDclNode funcdcl: node.funcDclList) {
            visit((OtherFuncDclNode) funcdcl);
        }
        nowclass = null;
        curscope = tmp;
    }

    void get_classscope(ClassDclNode dcl, BaseScope nowscope) {
        for(VarDclNode vardcl: dcl.varDcList) {
            if(vardcl instanceof AssignDclNode)
                throw new semanticError("Class member declaration error", vardcl.pos);
            if(vardcl instanceof SimpleDclNode) {
                for(OneDclNode nowdcl: ((SimpleDclNode) vardcl).dcls) {
                    nowscope.vardefine(new VarSymbol(nowdcl));
                }
            }
            if(vardcl instanceof OneDclNode)
                nowscope.vardefine(new VarSymbol((OneDclNode) vardcl));
        }
        for(OtherFuncDclNode funcdcl:dcl.funcDclList) {
            if(funcdcl.type == null) {
                if(!funcdcl.identifier.equals(dcl.identifier))
                    throw new semanticError("Function must have type", funcdcl.pos);
                if(funcdcl.dcls.dcls.size() > 0)
                    throw new semanticError("Constructor mustn't have parameters", funcdcl.dcls.pos);
                nowscope.constructordefine(new FuncSymbol(funcdcl));
            }
            else nowscope.funcdefine(new FuncSymbol(funcdcl));
        }
    }

    @Override
    public void visit(ProgramNode node) {
        int hasmain = 0;
        for(DclNode dcl:node.dcls) {
            if(dcl instanceof ClassDclNode) {
                GlobalScope nowscope = new GlobalScope();
                get_classscope((ClassDclNode) dcl, nowscope);
                globalscope.classdefine(new ClassSymbol(((ClassDclNode) dcl).identifier, nowscope, dcl));
            }
            if(dcl instanceof FuncDclNode) {
                if(dcl instanceof MainFuncDclNode) {
                    globalscope.funcdefine(new FuncSymbol((MainFuncDclNode) dcl));
                    hasmain = 1;
                }
                else {
                    if(((OtherFuncDclNode) dcl).identifier.equals("main"))
                        throw new semanticError("Function \"main\" declaration error", dcl.pos);
                    globalscope.funcdefine(new FuncSymbol((OtherFuncDclNode) dcl));
                }
            }
        }
        if(hasmain == 0) throw new semanticError("There is no main function", node.pos);
        curscope = new LocalScope(globalscope);
        loopcnt = 0;
        funcType = null;
        node.dcls.forEach(x -> x.accept(this));
    }
}
