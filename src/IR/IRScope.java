package IR;

import IR.operand.GlobalVar;
import IR.operand.Operand;

import java.util.HashMap;
import java.util.Map;

public class IRScope {
    IRScope fa;
    Map<String, Function> funcMap;
    Map<String, Operand> varMap;
    Map<String, GlobalVar> strMap;

    public IRScope(IRScope _fa) {
        fa = _fa;
        funcMap = new HashMap<>();
        varMap = new HashMap<>();
        strMap = new HashMap<>();
    }

    public void add_func(String name, Function func) {
        funcMap.put(name, func);
        //System.out.println("add_func: " + name);
    }
    public void add_var(String name, Operand var) {
        varMap.put(name, var);
    }
    public void add_str(String name, GlobalVar globalVar) {
        strMap.put(name, globalVar);
    }
    public Function get_func(String name) {
        if(funcMap.containsKey(name)) return funcMap.get(name);
        if(fa == null) return null;
        return fa.get_func(name);
    }
    public Operand get_var(String name) {
        if(varMap.containsKey(name)) return varMap.get(name);
        if(fa == null) return null;
        return fa.get_var(name);
    }
    public GlobalVar get_str(String name) {
        if(strMap.containsKey(name)) return strMap.get(name);
        if(fa == null) return null;
        return fa.get_str(name);
    }

}
