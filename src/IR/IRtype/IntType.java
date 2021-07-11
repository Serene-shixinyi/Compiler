package IR.IRtype;

import Util.error.unexpectedError;

public class IntType extends BaseType{
    public int size;
    public IntType(int _size) {
        super();
        size = _size;
        if(size != 8 && size != 32) {
            throw new unexpectedError("new intType size not 8 or 32");
        }
    }

    @Override
    public String toString() {
        return "i" + size;
    }
    @Override
    public boolean sameType(BaseType b) {
        return b instanceof IntType && b.size() == size;
    }
    @Override
    public int size() {
        return size;
    }
}
