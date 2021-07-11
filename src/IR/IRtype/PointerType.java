package IR.IRtype;

public class PointerType extends BaseType{
    public BaseType pointTo;
    public int dim;
    public PointerType(BaseType _pointTo) {
        super();
        pointTo = _pointTo;
        dim = _pointTo.dim() + 1;
    }

    @Override
    public String toString() {
        if (pointTo instanceof VoidType) return "";
        return pointTo.toString() + "*";
    }
    @Override
    public boolean sameType(BaseType b) {
        return (b instanceof PointerType &&
                (((PointerType) b).pointTo instanceof VoidType || ((PointerType) b).pointTo.sameType(pointTo)))
                || (b instanceof ArrayType && b.sameType(this));
    }
    @Override
    public int size() { return 32; }
    public int dim() {return dim;}
}
