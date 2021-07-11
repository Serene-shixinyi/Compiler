package IR.IRtype;

import java.util.HashMap;
import java.util.Map;

public class TypeTable {
    public Map<String, BaseType> typeTable;

    public TypeTable() {
        typeTable = new HashMap<>();
        typeTable.put("int", new IntType(32));
        typeTable.put("bool", new IntType(8)); //qaq
        typeTable.put("string", new PointerType(new IntType(8)));
        typeTable.put("void", new VoidType());
    }

    public void add_type(String key, ClassType type) {
        typeTable.put(key, type);
    }

    public BaseType get_type(String key) {
        return typeTable.get(key);
    }
}
