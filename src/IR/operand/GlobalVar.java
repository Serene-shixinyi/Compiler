package IR.operand;

import IR.IRtype.BaseType;

public class GlobalVar extends Operand{
    public String name;
    public Operand operand; //init
    public GlobalVar(BaseType _type, String _name) {
        super(_type);
        name = _name;
    }

    @Override
    public String toString() {
        return "@" + name;
    }
    @Override
    public boolean is_const() {return false;}
    public String defineToString() {
        StringBuilder string = new StringBuilder(toString() + " = ");
        if (operand instanceof ConstStr)
            string.append("private unnamed_addr constant ").
                    append(type.toString()).append(" ").append(operand.toString());
        else if(operand != null)
            string.append("global ").append(type.toString()).append(" ").append(operand.toString());
        else string.append("global ").append(type.toString());
        return string.toString();
    }
}
