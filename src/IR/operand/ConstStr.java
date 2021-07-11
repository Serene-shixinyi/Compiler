package IR.operand;

import IR.IRtype.BaseType;

public class ConstStr extends Operand{
    public String value;
    public ConstStr(BaseType _type, String _value){
        super(_type);
        value = _value;
    }

    @Override
    public String toString() {///////////
        String tmp = value;
        tmp = tmp.replace("\\", "\\5C");
        tmp = tmp.replace("\n", "\\0A");
        tmp = tmp.replace("\"", "\\22");
        tmp = tmp.replace("\0", "\\00");
        return "c\"" + tmp + "\"";
    }
    @Override
    public boolean is_const() {return true;}
}
