package Util.symbol;

import Util.position;
import Util.error.*;

public class LocalScope extends BaseScope{
    public LocalScope(Scope _fa) {
        super(_fa);
    }

    @Override
    public Scope get_fa(){ return super.fa; }
    @Override
    public void vardefine(VarSymbol _sym) {
        if(super.varmap.containsKey(_sym.identifier)) throw new semanticError("Duplicate identifiers.", _sym.define.pos);
        if(super.classmap.containsKey(_sym.identifier)) throw new semanticError("Duplicate identifiers.", _sym.define.pos);
        super.varmap.put(_sym.identifier, _sym);
    }
    @Override
    public void funcdefine(FuncSymbol _sym) {
        if(super.funcmap.containsKey(_sym.identifier)) throw new semanticError("Duplicate identifiers.", _sym.define.pos);
        if(super.classmap.containsKey(_sym.identifier)) throw new semanticError("Duplicate identifiers.", _sym.define.pos);
        super.funcmap.put(_sym.identifier, _sym);
    }
    @Override
    public void constructordefine(FuncSymbol _sym) {
        if(super.funcmap.containsKey(_sym.identifier)) throw new semanticError("Duplicate identifiers.", _sym.define.pos);
        super.funcmap.put(_sym.identifier, _sym);
    }
    public void classdefine(ClassSymbol _sym) {
        throw new unexpectedError("local class define");
    }
    @Override
    public Symbol find_varsymbol(String _identifier, position _pos) {
        Symbol symbol = varmap.get(_identifier);
        if(symbol == null) return get_fa().find_varsymbol(_identifier, _pos);
        return symbol;
    }
    @Override
    public Symbol find_funcsymbol(String _identifier, position _pos) {
        Symbol symbol = funcmap.get(_identifier);
        if(symbol == null) return get_fa().find_funcsymbol(_identifier, _pos);
        return symbol;
    }
    @Override
    public Symbol find_classsymbol(String _identifier, position _pos) {
        Symbol symbol = classmap.get(_identifier);
        if(symbol == null) return get_fa().find_classsymbol(_identifier, _pos);
        return symbol;
    }
}
