package Util.symbol;

import Util.error.semanticError;
import Util.position;

public class GlobalScope extends BaseScope{
    public GlobalScope() {
        super(null);
    }

    public Scope get_fa(){ return super.fa; }
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
    @Override
    public void classdefine(ClassSymbol _sym) {
        if(super.funcmap.containsKey(_sym.identifier)) throw new semanticError("Duplicate identifiers.", _sym.define.pos);
        if(super.classmap.containsKey(_sym.identifier)) throw new semanticError("Duplicate identifiers.", _sym.define.pos);
        super.classmap.put(_sym.identifier, _sym);
    }
    @Override
    public Symbol find_varsymbol(String _identifier, position _pos) {
        Symbol symbol = varmap.get(_identifier);
        if(symbol == null) throw new semanticError("Unknown Identifier", _pos);
        return symbol;
    }
    @Override
    public Symbol find_funcsymbol(String _identifier, position _pos) {
        Symbol symbol = funcmap.get(_identifier);
        if(symbol == null) throw new semanticError("Unknown Identifier", _pos);
        return symbol;
    }
    @Override
    public Symbol find_classsymbol(String _identifier, position _pos) {
        Symbol symbol = classmap.get(_identifier);
        if(symbol == null) throw new semanticError("Unknown Identifier", _pos);
        return symbol;
    }
}
