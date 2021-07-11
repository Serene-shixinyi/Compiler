package Util.symbol;

import AST.ASTNode;

public class ClassSymbol extends Symbol{
    public FuncSymbol constructor;
    public Scope scope;

    public ClassSymbol(String _identifier, Scope _scope, ASTNode _define) {
        super(_identifier, _define);
        constructor = null;
        scope = _scope;
    }
}
