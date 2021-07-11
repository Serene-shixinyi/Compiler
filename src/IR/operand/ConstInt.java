package IR.operand;

import IR.IRtype.IntType;

public class ConstInt extends Operand{
    public int value;
    public ConstInt(int _value, int _size) {
        super(new IntType(_size));
        value = _value;
    }
    @Override
    public String toString() {
        return String.valueOf(value);
    }
    @Override
    public boolean is_const() {return true;}
}
