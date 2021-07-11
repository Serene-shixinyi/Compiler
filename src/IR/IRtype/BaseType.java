package IR.IRtype;

public abstract class BaseType {
    public BaseType(){}

    public int dim() { return 0; }
    public abstract String toString();
    public abstract boolean sameType(BaseType b);
    public abstract int size();
    public boolean equals(BaseType tp) {
        return toString().equals(tp.toString());
    }
}
