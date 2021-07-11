package IR.operand;

import IR.IRtype.IntType;

public class ConstBool extends Operand{
    public boolean value;
    public ConstBool(boolean _value) {
        super(new IntType(8)); //let it be a byte
        value = _value;
    }
    @Override
    public String toString() {
        return String.valueOf(value);
    }
    @Override
    public boolean is_const() {return true;}
}
