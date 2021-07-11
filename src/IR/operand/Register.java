package IR.operand;

import IR.IRtype.BaseType;
import IR.inst.Inst;

public class Register extends Operand{
    public String name;
    public Inst def;
    public Register(BaseType _type, String _name) {
        super(_type);
        name = _name;
    }

    @Override
    public String toString() {
        return "%" + name;
    }
    @Override
    public boolean is_const() {return false;}
}
