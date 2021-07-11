package Util;

import org.antlr.v4.runtime.Token;

public class position {
    public int row, col;
    public position(int _row, int _col) {
        this.row = _row;
        this.col = _col;
    }
    public position(Token token) {
        this.row = token.getLine();
        this.col = token.getCharPositionInLine();
    }
//    public int get_row() {return row;}
//    public int get_col() {return col;}

    public String toString() {return row + "," + col;}
}
