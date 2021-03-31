package AST;

import Util.position;

public class BinaryOpNode extends ExprNode{
    public enum Op{
        MUL, DIV, MOD, ADD, SUB, SHL, SHR, LT, GT, LEQ, GEQ, EQ, NEQ, AND, XOR, OR, AND2, OR2, ASSIGN
    } //  * / % + - << >> < > <= >= == != & ^ | && || =

    public Op op;
    public ExprNode l, r;

    public BinaryOpNode(position _pos, ExprNode _l, ExprNode _r, Op _op) {
        super(_pos);
        l = _l; r = _r; op = _op;
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
