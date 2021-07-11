package IR.IRtype;

public class ArrayType extends BaseType{
    public int num;
    public BaseType type;
    public ArrayType(int _num, BaseType _type) {
        super();
        num = _num;
        type = _type;
    }
    @Override
    public boolean sameType(BaseType b) {
        return b instanceof PointerType && (((PointerType)b).pointTo.sameType(type) ||
                ((PointerType) b).pointTo instanceof VoidType) ||
                b instanceof ArrayType && ((ArrayType)b).type.sameType(type);
    }
    @Override
    public String toString() {
        return "[ " + num + " x " + type.toString() + " ]";
    }
    @Override
    public int size() {
        return type.size();
    }
}
