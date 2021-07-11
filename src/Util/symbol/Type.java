package Util.symbol;

import AST.*;
import Util.error.unexpectedError;

public class Type {
    public String typename;
    public int dim;
    public Type(String _typename, int _dim) {
        typename = _typename;
        dim = _dim;
        if(_dim < 0) throw new RuntimeException();
        if(_typename.equals("null") && dim > 0) throw new RuntimeException();
        if(_typename.equals("void") && dim > 0) throw new RuntimeException();
    }
    public Type(TypeNode node) {
        typename = node.typeIdentifier;
        if(node instanceof ArrayTypeNode) {
            dim = ((ArrayTypeNode) node).dim;
        }
        else dim = 0;
    }

    public boolean is_bool() {
        return typename.equals("bool") && dim == 0;
    }
    public boolean is_int() {
        return typename.equals("int") && dim == 0;
    }
    public boolean is_string() {
        return typename.equals("string") && dim == 0;
    }
    public boolean is_void() {
        return typename.equals("void") && dim == 0;
    }
    public boolean is_null() {
        return typename.equals("null") && dim == 0;
    }
    public boolean is_array() {
        return dim > 0;
    }
    public boolean is_class() {
        return dim == 0 && (!is_bool()) && (!is_int())
                && (!is_void()) && (!is_null()) && (!is_array());
    }

    public boolean equal(Type b) {
        //please don't use this for void type
        if(is_void() || b.is_void()) throw new unexpectedError("type function \"equal\" use for void");
        return typename.equals(b.typename) && dim == b.dim;
    }
    public boolean can_assign(Type b) {
        if(b.is_void()) return false;
        if(equal(b)) return true;
        if(is_null() || b.is_null()) {
            boolean fl1 = false, fl2 = false;
            if(is_null() || is_array() || is_string() || is_class()) fl1 = true;
            if(b.is_null() || b.is_array() || b.is_string() || b.is_class()) fl2 = true;
            return fl1 && fl2;
        }
        return false;
    }// = == !=
    public boolean can_compare(Type b) {
        if(is_int() && b.is_int()) return true;
        if(is_string() && b.is_string()) return true;
        return false;
    }// < > <= >= +
    public boolean can_calculate(Type b) {
        return is_int() && b.is_int();
    }// - * / << >> % ^ & |
    public boolean all_bool(Type b) {return is_bool() && b.is_bool();} // || &&
}
