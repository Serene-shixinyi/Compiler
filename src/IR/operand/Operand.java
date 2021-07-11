package IR.operand;

import IR.IRtype.BaseType;

public abstract class Operand {
    public BaseType type;
    public Operand(BaseType _type) {
        type = _type;
    }
    public abstract String toString();
    public abstract boolean is_const();
}
