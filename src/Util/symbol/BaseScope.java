package Util.symbol;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class BaseScope implements Scope{
    public Scope fa;
    Map<String, VarSymbol> varmap;
    Map<String, FuncSymbol> funcmap;
    public Map<String, ClassSymbol> classmap;

    public BaseScope(Scope _fa) {
        fa = _fa;
        varmap = new LinkedHashMap<>();
        funcmap = new LinkedHashMap<>();
        if(_fa != null)
            classmap = ((BaseScope) fa).classmap;
        else classmap = new LinkedHashMap<>();
    }

//    @Override
//    public Scope get_fa(){ return fa; }
//    @Override
//    public void vardefine(VarSymbol _sym) {
//        throw new unexpectedError("variable define");
//    }
//    @Override
//    public void funcdefine(FuncSymbol _sym) {
//        throw new unexpectedError("function define");
//    }
//    @Override
//    public void classdefine(ClassSymbol _sym) {
//        throw new unexpectedError("class define");
//    }
//    @Override
//    public Symbol find_varsymbol(String _identifier, position _pos) {
//        throw new RuntimeException();
//    }
//    @Override
//    public Symbol find_funcsymbol(String _identifier, position _pos) {
//        throw new RuntimeException();
//    }
//    @Override
//    public Symbol find_classsymbol(String _identifier, position _pos) {
//        throw new RuntimeException();
//    }
}