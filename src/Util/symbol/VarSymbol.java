package Util.symbol;

import AST.ASTNode;
import AST.OneDclNode;

public class VarSymbol extends Symbol{
    public Type type;
    public VarSymbol(String _name, Type _type, ASTNode _define) {
        super(_name, _define);
        type = _type;
    }
    public VarSymbol(OneDclNode node) {
        super(node.identifier, node);
        type = new Type(node.type);
    }

}
