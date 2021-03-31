package Util.symbol;

import Util.position;

public interface Scope {
    public Scope get_fa();
    public void vardefine(VarSymbol _sym);
    public void funcdefine(FuncSymbol _sym);
    public void constructordefine(FuncSymbol _sym);
    public void classdefine(ClassSymbol _sym);

    public Symbol find_varsymbol(String _identifier, position _pos);
    public Symbol find_funcsymbol(String _identifier, position _pos);
    public Symbol find_classsymbol(String _identifier, position _pos);
}
