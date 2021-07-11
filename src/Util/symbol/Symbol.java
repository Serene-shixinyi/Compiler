package Util.symbol;

import AST.ASTNode;

public class Symbol {
    public String identifier;
    public ASTNode define;
//    public Scope scope;
    public Symbol(String _identifier, ASTNode _define) {
        identifier = _identifier;
        define = _define;
//        type = _type;
//        scope = _scope;
    }
}
