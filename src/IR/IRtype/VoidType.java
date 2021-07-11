package IR.IRtype;

import Util.error.unexpectedError;

public class VoidType extends BaseType{
    public VoidType() {
        super();
    }

    @Override
    public String toString() {
        return "void";
    }
    @Override
    public boolean sameType(BaseType b) {
        return b instanceof VoidType;
    }
    @Override
    public int size() {
        throw new unexpectedError("ask the size of void type");
    }
}
