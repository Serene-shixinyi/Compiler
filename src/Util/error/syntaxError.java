package Util.error;

import Util.position;

public class syntaxError extends error{
    private position pos;

    public syntaxError(String _msg, position _pos) {
        super("SyntaxError: " + _msg);
        pos = _pos;
    }
    public String toString() {return super.msg + pos.toString();}
}
