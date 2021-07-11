package IR.operand;

import IR.IRtype.*;

public class Null extends Operand{
    public Null() {
        super(new PointerType(new VoidType()));
    }

    @Override
    public String toString() {
        return "null";
    }
    @Override
    public boolean is_const() {return false;}
}
