package Util.symbol;

import AST.ASTNode;
import AST.MainFuncDclNode;
import AST.OneDclNode;
import AST.OtherFuncDclNode;

import java.util.ArrayList;
import java.util.List;

public class FuncSymbol extends Symbol{
    public Type type;
    public List<Type> prmts;

    public FuncSymbol(String _identifier, Type _type, ASTNode _define) {
        super(_identifier, _define);
        type = _type;
        prmts = new ArrayList<>();
    }
    public FuncSymbol(OtherFuncDclNode node) {
        super(node.identifier, node);
        if(node.type != null) type = new Type(node.type);
        else type = new Type("void", 0);
        prmts = new ArrayList<>();
        for(OneDclNode dcl: node.dcls.dcls) {
            prmts.add(new Type(dcl.type));
        }
    }
    public FuncSymbol(MainFuncDclNode node) {
        super("main", node);
        type = new Type("int", 0);
        prmts = new ArrayList<>();
    }
}
