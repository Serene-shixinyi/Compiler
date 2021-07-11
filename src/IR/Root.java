package IR;

import IR.IRtype.*;
import IR.operand.Register;

public class Root {
    public TypeTable typeTable;
    public int lable;
    public IRScope globalScope;

    public Root() {
        typeTable = new TypeTable();
        lable = 0;
        globalScope = new IRScope(null);

        //builtin functions
        BaseType returnType;
        Function func;

        // byte* malloc(int size);
        returnType = new PointerType(new IntType(8));
        func = new Function("_malloc", returnType, true);
        func.add_parameter(new Register(new IntType(32), "size"));
        globalScope.add_func("_malloc", func);

        // void print(string str);
        returnType = new VoidType();
        func = new Function("print", returnType, true);
        func.add_parameter(new Register(new PointerType(new IntType(8)), "str"));
        globalScope.add_func("print", func);

        // void println(string str);
        returnType = new VoidType();
        func = new Function("println", returnType, true);
        func.add_parameter(new Register(new PointerType(new IntType(8)), "str"));
        globalScope.add_func("println", func);

        // void printInt(int n);
        returnType = new VoidType();
        func = new Function("printInt", returnType, true);
        func.add_parameter(new Register(new IntType(32), "n"));
        globalScope.add_func("printInt", func);

        // void printlnInt(int n);
        returnType = new VoidType();
        func = new Function("printlnInt", returnType, true);
        func.add_parameter(new Register(new IntType(32), "n"));
        globalScope.add_func("printlnInt", func);

        // string getString();
        returnType = new PointerType(new IntType(8));
        func = new Function("getString", returnType, true);
        globalScope.add_func("getString", func);

        // int getInt();
        returnType = new IntType(32);
        func = new Function("getInt", returnType, true);
        globalScope.add_func("getInt", func);

        // string toString(int i);
        returnType = new PointerType(new IntType(8));
        func = new Function("toString", returnType, true);
        func.add_parameter(new Register(new IntType(32), "i"));
        globalScope.add_func("toString", func);

        //string series

        // string string.concatenate(string str1, string str2);
        returnType = new PointerType(new IntType(8));
        func = new Function("__string_concatenate", returnType, true);
        func.add_parameter(new Register(new PointerType(new IntType(8)), "str1"));
        func.add_parameter(new Register(new PointerType(new IntType(8)), "str2"));
        globalScope.add_func("__string_concatenate", func);

        // bool string.equal(string str1, string str2);
        returnType = new IntType(8); //bool
        func = new Function("__string_equal", returnType, true);
        func.add_parameter(new Register(new PointerType(new IntType(8)), "str1"));
        func.add_parameter(new Register(new PointerType(new IntType(8)), "str2"));
        globalScope.add_func("__string_equal", func);

        // bool string.notEqual(string str1, string str2);
        returnType = new IntType(8); //bool
        func = new Function("__string_notEqual", returnType, true);
        func.add_parameter(new Register(new PointerType(new IntType(8)), "str1"));
        func.add_parameter(new Register(new PointerType(new IntType(8)), "str2"));
        globalScope.add_func("__string_notEqual", func);

        // bool string.lessThan(string str1, string str2);
        returnType = new IntType(8); //bool
        func = new Function("__string_lessThan", returnType, true);
        func.add_parameter(new Register(new PointerType(new IntType(8)), "str1"));
        func.add_parameter(new Register(new PointerType(new IntType(8)), "str2"));
        globalScope.add_func("__string_lessThan", func);

        // bool string.greaterThan(string str1, string str2);
        returnType = new IntType(8); //bool
        func = new Function("__string_greaterThan", returnType, true);
        func.add_parameter(new Register(new PointerType(new IntType(8)), "str1"));
        func.add_parameter(new Register(new PointerType(new IntType(8)), "str2"));
        globalScope.add_func("__string_greaterThan", func);


        // bool string.lessEqual(string str1, string str2);
        returnType = new IntType(8); //bool
        func = new Function("__string_lessEqual", returnType, true);
        func.add_parameter(new Register(new PointerType(new IntType(8)), "str1"));
        func.add_parameter(new Register(new PointerType(new IntType(8)), "str2"));
        globalScope.add_func("__string_lessEqual", func);

        // bool string.greaterEqual(string str1, string str2);
        returnType = new IntType(8); //bool
        func = new Function("__string_greaterEqual", returnType, true);
        func.add_parameter(new Register(new PointerType(new IntType(8)), "str1"));
        func.add_parameter(new Register(new PointerType(new IntType(8)), "str2"));
        globalScope.add_func("__string_greaterEqual", func);

        // int string.length(string str);
        returnType = new IntType(32);
        func = new Function("__string_length", returnType, true);
        func.add_parameter(new Register(new PointerType(new IntType(8)), "str"));
        globalScope.add_func("__string_length", func);

        // string string.substring(string str, int left, int right);
        returnType = new PointerType(new IntType(8));
        func = new Function("__string_substring", returnType, true);
        func.add_parameter(new Register(new PointerType(new IntType(8)), "str"));
        func.add_parameter(new Register(new IntType(32), "left"));
        func.add_parameter(new Register(new IntType(32), "right"));
        globalScope.add_func("__string_substring", func);

        // int string.parseInt(string str);
        returnType = new IntType(32);
        func = new Function("__string_parseInt", returnType, true);
        func.add_parameter(new Register(new PointerType(new IntType(8)), "str"));
        globalScope.add_func("__string_parseInt", func);

        // int ord(string str, int pos);
        returnType = new IntType(32);
        func = new Function("__string_ord", returnType, true);
        func.add_parameter(new Register(new PointerType(new IntType(8)), "str"));
        func.add_parameter(new Register(new IntType(32), "pos"));
        globalScope.add_func("__string_ord", func);

        /* int array.size(array arr);
        returnType = new IntType(32);
        func = new Function("__array_size", returnType, true);
        func.add_parameter(new Register(new PointerType(new IntType(8)), "arr"));
        globalScope.add_func("__array_size", func);
        */
    }

    public int get_lable() {
        lable = lable + 1;
        return lable;
    }
    public void add_class(ClassType type) {
        typeTable.add_type(type.name, type);
    }
    public BaseType get_type(String key) {
        return typeTable.get_type(key);
    }
    public String change_string(String str) {
        String rs = new String(str);
        rs = rs.replace("\\\\", "\\");
        rs = rs.replace("\\n", "\n");
        rs = rs.replace("\\\"", "\"");
        rs = rs + "\0";
        return rs;
    }

    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
