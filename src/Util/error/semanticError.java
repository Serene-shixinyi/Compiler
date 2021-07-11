package Util.error;

import Util.position;

public class semanticError extends error{
    private position pos;

    public semanticError(String _msg, position _pos) {
        super("SemanticError: " + _msg);
        pos = _pos;
    }
    public String toString() {return super.msg + pos.toString();}
}
